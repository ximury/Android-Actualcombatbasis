/*!
 * lhgcore Dialog Plugin v2.3.7
 * Date : 2010-02-06 16:15:11
 * Copyright (c) 2009 - 2010 By Li Hui Gang
 */

;(function(J){

var topWin = window, cover, topDoc, coDiv;
while( topWin.parent && topWin.parent != topWin )
{
	try{
		if( topWin.parent.document.domain != document.domain ) break;
		//假如你不想跨frameset只跨iframe就把下面这句的注释去掉
		//if( J('frameset',topWin.parent.document).length > 0 ) break;
	}
	catch(e){ break; }
	topWin = topWin.parent;
}
topDoc = topWin.document;

// 创建拖动时的层
coDiv = J('<div id="cDiv" style="position:absolute;top:0px;left:0px;visibility:hidden;border:1px solid #000;background-color:#999;"></div>',topDoc);

// 取窗口的zIndex值
function getZIndex()
{
	if( !J.dialog.ZIndex )
	    J.dialog.ZIndex = 1999;
	return ++J.dialog.ZIndex;
};

// 取指定的js文件的绝对路径，返回形式为：http://www.xxx.com/xxx/xxx/
function getMap( file )
{
	var bp, fp, s = J('script'), i = 0, l = s.length;
	
	for( ; i < l; i++ )
	{
		if( s[i].src.indexOf(file) >= 0 )
		{
		    bp = s[i].src.substr( 0, s[i].src.toLowerCase().indexOf(file) );
			break;
		}
	}
	
	if( typeof bp !== 'undefined' )
	{
		if( !J.browser.ie || bp.indexOf('http') === 0 ||
			(J.browser.i8 && J.dialog.isDTD(document)) )
			return bp;
		else
		{
			fp = window.location.href;
			fp = fp.substr( 0, fp.lastIndexOf('/') );
			
			if( bp.indexOf('../') !== -1 )
			{
				while( bp.indexOf('../') >= 0 )
				{
					bp = bp.substr(3);
					fp = fp.substr( 0, fp.lastIndexOf('/') );
				}
				return fp + '/' + bp;
			}
			else if( bp.indexOf('/') === 0 )
			{
				fp = document.location.protocol + '//' + document.location.host;
				return fp + bp;
			}
			else
				return fp + '/' + bp;
		}
	}
};

// 窗口大小改变时重新计算遮罩层的尺寸
function reSizeHdl()
{
	var rel = J.dialog.isDTD( topDoc ) ? topDoc.documentElement : topDoc.body;
	
	J(cover).css({
		width: Math.max( rel.scrollWidth, rel.clientWidth, topDoc.scrollWidth || 0 ) - 1 + 'px',
		height: Math.max( rel.scrollHeight, rel.clientHeight, topDoc.scrollHeight || 0 ) - 1 + 'px'
	});
};

function setDefault( options )
{
	// 窗口的默认值的设置
	var _default = {
		width: 400,
		height: 300,
		title: 'lhgdialog',
		win: window,  //调用控件页面的window对象
		core: getMap( 'lhgcore.min.js' ),   //取lhgcore.min.js路径，用在lhgdialog.html里加载此文件
		skin: 'default',    //窗口默认皮肤的目录
		drag: true,   //是否充许拖动操作
		foot: true,   //是否显示页脚
		topW: topWin,   //取顶层窗口页面（也就是控件创建的页面）的window对象
		cDiv: coDiv   // 拖动时拖动的层对象
	};
	
	return J.extend( _default, options || {} );
};

J.dialog =
{
    indoc: {}, inwin: {}, innwin: {}, infrm: {}, inndoc: {},
	
	get: function( id, options )
	{
		if( !id || J('#'+id,topDoc)[0] ) return;
		
		var r = setDefault( options );
		
		if( r.link && (!/\http:/.test(r.link) || r.link.indexOf(document.domain) >= 0) )
		{
		    alert( 'Warning: link Parameter Value Error!' );
			return false;
		}
		
		//是否打开遮罩层
		if( r.cover ) this.dispCover();
		
		//是否从缓存读取内容页
		if( r.page && r.cache === false )
		    r.page = ( /\?/.test(r.page) ? r.page + '&' :
			    r.page + '?' ) + 'uuid=' + J.now();
		
		//插件路径，注意lhgdialog.js和lhgdialog.html一定要在同一目录下
		var dpath = getMap( 'lhgdialog.js' ),
		    view = this.client( topWin ), scro = this.scroll( topWin ),
		
		iTop = r.top ? r.top + scro.y :
			Math.max( scro.y + ( view.h - r.height - 20 ) / 2, 0 ),
		iLeft = r.left ? r.left + scro.x :
			Math.max( scro.x + ( view.w - r.width - 20 ) / 2, 0 ),
		
		dialog = J('<iframe frameborder="0" scrolling="no" allowTransparency="true" id="' + id + '" src="' + dpath +
		    'lhgdialog.jsp" style="top:' + iTop + 'px;left:' + iLeft + 'px;position:absolute;width:' + r.width +
			'px;height:' + r.height + 'px;z-index:' + getZIndex() + ';"></iframe>',topDoc).appendTo( topDoc.body )[0];
		
		dialog._dlgargs = r; dialog = null;
	},
	
	getsrc: function()
	{
		if( J.browser.ie )
			return ( J.browser.i7 ? '' : "javascript:''" );
		else
			return 'javascript:void(0)';
	},
	
	isDTD: function( doc )
	{
	    return ( 'CSS1Compat' == ( doc.compatMode || 'CSS1Compat' ) );
	},
		
	// 取客户端的可视页面尺寸
	client: function( win )
	{
		win = win || window;
		
		if( J.browser.ie )
		{
			var oSize, doc = win.document.documentElement;
			if( doc && doc.clientWidth ) oSize = doc; else oSize = win.document.body;
			
			if( oSize )
				return { w : oSize.clientWidth, h : oSize.clientHeight };
			else
				return { w : 0, h : 0 };
		}
		else
			return { w : win.innerWidth, h : win.innerHeight };
	},
	
	// 取客户端的页面滚动尺寸
	scroll: function( win )
	{
		win = win || window;
		
		if( J.browser.ie )
		{
			var doc = win.document;
			oPos = { x : doc.documentElement.scrollLeft, y : doc.documentElement.scrollTop };
			if( oPos.x > 0 || oPos.y > 0 ) return oPos;
			
			return { x : doc.body.scrollLeft, y : doc.body.scrollTop };
		}
		else
			return { x : win.pageXOffset, y : win.pageYOffset };
	},
	
	// 关闭窗口函数
	close: function( dlgWin )
	{
		var dlg = dlgWin.frameElement;
		
		if( dlg )
		{
			dlg.src = this.getsrc();
			J(dlg).remove(); dlg = null;
		}
		
		if( cover )
			cover.css('display','none');
	},
	
	//显示遮罩层
	dispCover: function()
	{
		if( !cover )
		{
			cover = J('<div style="position:absolute;top:0px;left:0px;' +
				'background-color:#fff;"></div>',topDoc).css('opacity',0.5).appendTo(topDoc.body);
			
			if( J.browser.ie && !J.browser.i7 )
			{
				J('<iframe hideFocus="true" frameborder="0" src="' + this.getsrc() + '" style="width:100%;height:100%;' +
				  'top:0px;left:0px;filter:progid:DXImageTransform.Microsoft.Alpha(opacity=0)"></iframe>',topDoc).appendTo(cover[0]);
			}
		}
		
		J( topWin ).bind( 'resize', reSizeHdl ); reSizeHdl(); cover.css({ display: '', zIndex: getZIndex() });
	}
};

J(window).bind( 'unload', function(){
    coDiv.remove(); coDiv = null;
	if( cover ) cover.remove(); cover = null;
});

})(lhgcore);
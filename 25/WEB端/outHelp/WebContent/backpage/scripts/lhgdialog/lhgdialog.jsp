<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--  
 * lhgcore Dialog Plugin v2.3.7
 * Date : 2010-02-06 16:15:11
 * Copyright (c) 2009 - 2010 By Li Hui Gang
 -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
var E = frameElement, W = E._dlgargs.win, B = W.J.dialog;

//取窗口设置参数，使用函数是为了防止内存泄露
function A(){ return E._dlgargs; }
window.focus();

//加载核心库文件 - lhgcore.min.js
document.write( '<' + 'script type="text/javascript" src="' + A().core + 'lhgcore.min.js"><' + '\/script>' );
	</script>
	<script type="text/javascript">
if( J.browser.ie )
    try{ document.execCommand('BackgroundImageCache',false,true); }catch(e){}

//样式表文件路径，注意skins文件夹一定要和lhgdialog.html在同一目录下
document.write( '<' + 'link href="skins/' + A().skin + '/lhgdialog.css" type="text/css" rel="stylesheet"/>' );

//修正IE6下窗口尺寸文件 - lhg_dlg_ie6.js
if( J.browser.ie && !J.browser.i7 )
    document.write( '<' + 'script type="text/javascript" src="skins/' + A().skin + '/lhg_dlg_ie6.js"><' + '\/script>' );

//用来计算加载内容页的iframe和窗口的尺寸
var S = (function()
{
    var ret = {
	    //计算内容区的大小
		contain: function()
		{
			var height = J('#contain')[0].offsetHeight -
			    ( J('#dfoot')[0] ? J('#dfoot')[0].offsetHeight : 0 );
			
			if( J('#lhgfrm')[0] )
				J('#lhgfrm').css( 'height', Math.max(height,0) + 'px' );
			else
				J('#dinner').css( 'height', Math.max(height,0) + 'px' );
		},
		
		//重新设置窗口的大小
		dialog: function( width, height )
		{
		    J(E).css({
			    'width': width + 'px', 'height': height + 'px'
			});
			
			if( J.isFunction(window.doretsize) )
			    window.doretsize();
		},
		
		auto: function()
		{
		    var lhgfrm = J('#lhgfrm')[0],
			    inndoc = lhgfrm.contentWindow.document,
				isDTD = B.isDTD( inndoc ),
			
			innerWidth = isDTD ? inndoc.documentElement.scrollWidth : inndoc.body.scrollWidth,
			innerHeight = isDTD ? inndoc.documentElement.scrollHeight : inndoc.body.scrollHeight,
			
			frameSize = B.client( lhgfrm.contentWindow ),
			
			deltaWidth	= innerWidth - frameSize.w,
			deltaHeight	= innerHeight - frameSize.h;
			
			if( deltaWidth <= 0 && deltaHeight <= 0 )
			    return;
			
			var dialogWidth = frameElement.offsetWidth + Math.max( deltaWidth, 0 ),
			    dialogHeight = frameElement.offsetHeight + Math.max( deltaHeight, 0 );
			
			this.dialog( dialogWidth, dialogHeight );
			this.contain();
		}
	};
	
	if( J.browser.ie && !J.browser.i7 )
	{
	    var original = ret.contain;
		ret.contain = function()
		{
		    return window.setTimeout( function(){ original.apply(ret); }, 1 );
		};
	}
	
	J(window).resize( function(){ ret.contain(); });
	
	return ret;
})();

//拖动操作代码
var D = (function()
{
    var regwin = [], lacoor, curpos, fW, fH,
	    view = B.client(A().topW), scro = B.scroll(A().topW);
	
	var clearUpHdl = function()
	{
	    for( var i = 0, l = regwin.length; i < l; i++ )
		{
			J( regwin[i].document ).unbind( 'mousemove', moveHdl );
			J( regwin[i].document ).unbind( 'mouseup', upHdl );
		}
	};
	
	var moveHdl = function(evt)
	{
	    if( !lacoor ) return;
		
		var curcoor = { x : evt.screenX, y : evt.screenY };
		curpos =
		{
		    x : curpos.x + ( curcoor.x - lacoor.x ),
			y : curpos.y + ( curcoor.y - lacoor.y )
		};
		lacoor = curcoor;
		
		//限制在浏览器内拖动
		if( A().rang )
		{
			//限制了上和左2个方向
			if( curpos.x < scro.x ) curpos.x = scro.x;
			if( curpos.y < scro.y ) curpos.y = scro.y;
			
			//限制了右和下2个方向，如果你不相限制这2个方向把下面代码删除即可
			if( curpos.x + fW > view.w + scro.x )
			    curpos.x = view.w + scro.x - fW;
			if( curpos.y + fH > view.h + scro.y )
			    curpos.y = view.h + scro.y - fH;
		}
		
		J(A().cDiv).css({ left: curpos.x + 'px', top: curpos.y + 'px' });
	};
	
	var upHdl = function(evt)
	{
	    if( !lacoor ) return;
		
		if( J.browser.ie )
		    evt.target.releaseCapture();
			
		clearUpHdl(); lacoor = null;
		A().cDiv.css('visibility','hidden');
		
		J(E).css({ left: curpos.x + 'px', top: curpos.y + 'px' });
	};
	
	return {
	    downHdl : function(evt)
		{
			if( evt.target.id == 'xbtn' ) return;
			
			fW = E.offsetWidth, fH = E.offsetHeight;
			
			curpos = { x : E.offsetLeft, y : E.offsetTop };
			lacoor = { x : evt.screenX, y : evt.screenY };
			
			A().cDiv.css({
			    width: fW + 'px', height: fH + 'px', left: curpos.x + 'px',
				top: curpos.y + 'px', zIndex: parseInt(B.ZIndex,10) + 2, visibility: ''
			});
			
			for( var i = 0, l = regwin.length; i < l; i++ )
			{
			    J( regwin[i].document ).bind( 'mousemove', moveHdl );
			    J( regwin[i].document ).bind( 'mouseup', upHdl );
			}
			
			evt.preventDefault();
			
			if( J.browser.ie ) evt.target.setCapture();
		},
		
		reghdl : function( win ){ regwin.push(win); }
	};
})();
	
(function()
{
	var loading = function( showIt )
	{
	    J('#throbber').css( 'visibility', showIt ? '' : 'hidden' );
	};
	
	var oid = E.id;
	
	J(window).load(function(){
		if( !J('#cDiv',A().topW.document)[0] )
		    A().cDiv.css('opacity',0.3)
			    .appendTo( A().topW.document.body )
			    .contextmenu( function(evt){ evt.preventDefault(); });
		
		//假如不显示页脚移除它
		if( !A().foot )
		    J('#dfoot').remove();
			
		loading(true); S.contain(); loadIFrm();
		
		//屏蔽右键菜单
		J(document).bind( 'contextmenu', function(evt){
		    evt.preventDefault();
		});
		
		//设置窗口的z-index值，使被单击的窗口总在最前面。
		if( J.browser.ie )
			J(document).bind( 'mousedown', setZIndex );
		else
		    J(window).bind( 'mousedown', setZIndex );
		
		//为窗口的调用页面的onunload事件绑定关闭窗口函数
		//此代码主要作用是调用窗口的页面如果被跳转，创建的窗口也一定要被关闭
		//因为在框架的项目调用窗口的页面和创建窗口的页面不是同一个页面，如不关闭窗口就会出错
		if( W != A().topW )
			J(W).bind( 'unload', function(){
				if( typeof cancel === 'function' ) cancel();
			});
		
		if( A().drag )
		{
		    J('#tc').mousedown( D.downHdl );
			D.reghdl( window ); D.reghdl(W);
			
			if( A().topW != W ) D.reghdl( A().topW );
		}
		
		//如果窗口使用link或html参数时隐藏等待层
		if( A().link || A().html ) loading(); setButton();
		
		//设置窗口的document对象和window对象
		//调用方式为：J.dialog.indoc['id'] - 窗口的document对象
	    //            J.dialog.inwin['id'] - 窗口的window对象
		//            J.dialog.infrm['id'] - 加载内容页的iframe对象
		//些二个对象主要是用对窗口和页面、窗口和窗口之间的传值
		B.indoc[oid] = document; B.inwin[oid] = window;
		B.infrm[oid] = J('#lhgfrm')[0] || {};
		
		//当使用html参数时，如果自定义参数为函数时，就执行函数
		//此功能主要用于使用html参数时可以对窗口页面的元素进行操作
		if( (A().html || A().link) && J.isFunction(A().custom) ) A().custom();
	});
	
	//加载窗口内容页
	var loadIFrm = function()
	{
	    if( window.onresize ) window.onresize();
		
		if( A().html ) J('#dinner').html( A().html );
		else
		{
		    var src = A().link ? A().link : A().page || B.getsrc(),
			css = A().link ? '' : 'style="visibility:hidden;"';
			J('#dinner').html( '<iframe id="lhgfrm" src="' + src + '" name="lhgfrm" ' +
			    'frameborder="0" width="100%" height="100%" scrolling="auto" ' + css + '><\/iframe>' );
		}
	};
	
	//内容页必须要调用函数，用于对窗口进行一些必要设置
	window.setDialog = function()
	{
	    if( !E.parentNode ) return null;
		
		var frmain = J('#lhgfrm')[0],
		innwin = frmain.contentWindow, inndoc = innwin.document;
		
		//设置加载窗口内容页的iframe对象的document和window对象
		//调用方式为：J.dialog.inndoc['id'] - frmain的document对象
		//            J.dialog.innwin['id'] - frmain的window对象
		//只有在内容页里调用loadinndlg函数这2个对象才能使用
		B.inndoc[oid] = inndoc; B.innwin[oid] = innwin;
		
		frmain.style.visibility = '';
		
		if( J.browser.ie )
		    J( inndoc ).mousedown( setZIndex );
		else
		    J( innwin ).mousedown( setZIndex );
		
		//此函数最后返回的W指的是窗口插件调用页面的window对象
		D.reghdl( innwin ); S.contain();
		innwin.focus(); loading(); frmain = null; return W;
	};
	
	//窗口的关闭函数 - B.close()调用的是J.dialog对象的关闭函数
	window.cancel = function(){ return closedlg(); };
	var closedlg = function()
	{
	    loading(); var ifrm = J('#lhgfrm')[0];
		if( ifrm )
		    ifrm.src = B.getsrc();
		ifrm = null; B.close( window );
	};
	
	var setZIndex = function(evt)
	{
		J(E).css( 'zIndex', parseInt(B.ZIndex,10) + 1 );
		B.ZIndex = parseInt( E.style.zIndex, 10 );
		
		evt.stopPropagation();
	};
	
	//设置窗口右上角的X关闭按钮事件，加载窗口的标题。
	var setButton = function()
	{
		//xbtn为右上角的X关闭按钮的id
		J('#xbtn').hover(function(){
		    J(this).addClass('xbtnover');
		},function(){
		    J(this).removeClass('xbtnover');
		}).click( cancel );
		
		J('#txt').html( A().title );
		
		//创建取消按钮，如你不想插件有默认的取消按钮的话把下面这句代码删除即可
		addBtn( 'cbtn', '取 消', cancel );
	};
	
	//创建按钮函数，参数为id-按钮的id，txt-按钮的文本，fn-按钮单击绑定的函数
	window.addBtn = function( id, txt, fn )
	{
	    if( J('#'+id)[0] ){ J('#'+id).html( '<span>' + txt + '</span>' ).click(fn); }
		else
		{
			var li = document.createElement('li');
			
			J(li).attr('id',id).hover(function(){
			    J(this).addClass('btnover');
			},function(){
			    J(this).removeClass('btnover');
			}).addClass('sbtn').click(fn).appendTo('#btns');
			
			J(li).html( '<span>' + txt + '</span>' );
		}
	};
	
	//移除指定的按钮
	window.removeBtn = function(id)
	{
	    if( J('#'+id)[0] ) J('#'+id).remove();
	};
})();
	</script>
</head>

<body>
    <div id="contain" class="contain">
	    <div id="dinner" class="dlginner"></div>
		<div id="dfoot" class="dlgfoot"><ul id="btns"></ul></div>
	</div>
	<div class="tl"></div>
	<div id="tc" class="tc">
	    <div class="ico"></div><div id="txt"></div><div id="xbtn" class="xbtn"></div>
	</div>
	<div class="tr"></div>
	<div class="ml"></div>
	<div class="mr"></div>
	<div class="bl"></div>
	<div class="bc"></div>
	<div id="br" class="br"></div>
	<div id="throbber" style="position:absolute;visibility:hidden;">正在加载窗口内容，请稍等....</div>
</body>
</html>
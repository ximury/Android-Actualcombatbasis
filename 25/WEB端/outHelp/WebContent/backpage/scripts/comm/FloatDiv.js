//bg是否显示背景，msg显示消息
function LoadImg(bg, msg) {//加载数据提示框
	 openMask();
    if (bg == null) { bg = false }
    if (msg == null) { msg = ""; }
    if ($("divLoadImg") == null) {
        var divcenter = document.createElement("DIV");
        divcenter.id = "divLoadImg";
        document.body.appendChild(divcenter);
    }
    if (!bg)
        $("divLoadImg").className = "divLoad";
    else
        $("divLoadImg").className = "divLoadbg";
    //var centerhtml = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='right' width='40' style='padding-top:15px; padding-right:6px;'><img src='"+contextPath+"/Images/loading.gif' alt='' /></td><td  style='padding-top:16px;'>" + msg + "</td></tr></table>";
    var centerhtml = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='right' width='40' style='padding-top:15px; padding-right:6px;'></td><td  style='padding-top:16px;'>" + msg + "</td></tr></table>";

    $("divLoadImg").innerHTML = centerhtml;
    var w = $("divLoadImg").getWidth();
    var h = $("divLoadImg").getHeight();
    var dh = document.viewport.getHeight(); //可视化区域的高度
    var dw = document.body.offsetWidth || document.documentElement.offsetWidth;
    var sh = document.body.scrollTop || document.documentElement.scrollTop; //已经滚动过的高度
    var th = (parseInt((dh - (h + 22)) / 2) - 30)
    th = th < 30 ? 30 : th;
    th += sh;
    var tw = parseInt((dw - w) / 2) < 0 ? 0 : parseInt((dw - w) / 2)
    $("divLoadImg").style.top = th + "px";
    $("divLoadImg").style.left = tw + "px";
   
    
}

function openMask() {//关闭提示框
    var dh = document.body.scrollHeight || document.documentElement.scrollHeight; //整个网页的高度
	   if ($("frmBg") == null) {
	        var frm = document.createElement("IFRAME");
	        frm.id = "frmBg";
	        frm.name = "frmBg";
	        frm.frameBorder = 0;
	        document.body.appendChild(frm);
	        var strHtm = "<html>\r\n";
	        strHtm += "<head>\r\n";
	        strHtm += "<scr" + "ipt type=\"text/javas" + "cript\">\r\n";
	        strHtm += "window.onload = function(){\r\n";
	        strHtm += "document.onmousedown = function(){parent.objFloatDiv.shining();}\r\n";
	        strHtm += "}\r\n";
	        strHtm += "</scr" + "ipt>\r\n";
	        strHtm += "</head>\r\n";
	        strHtm += "<body oncontextmenu='return false' style='background:#000000'>\r\n";
	        strHtm += "</body>\r\n";
	        strHtm += "</html>";
	        window.frmBg.document.writeln(strHtm);
	        window.frmBg.document.close();
	    }
	    else
	    $("frmBg").show();
	    $("frmBg").className = "OpBg" ;
	    $("frmBg").style.height = dh + "px";
	    
}

function closeMask() {//关闭提示框
	
	 if ($("frmBg") != null) {
         $("frmBg").hide();
     }
}

function CloseLoadImg() {//关闭提示框
	closeMask();
    if ($("divLoadImg") != null)
        $("divLoadImg").remove();
}


//-提示条----
function FloatMsg(html, bgcolor, w, h) {//正在保存的提示条
    if ($("divFloatMsg") == null) {
        var divcenter = document.createElement("DIV");
        divcenter.id = "divFloatMsg";
        divcenter.className = "divLoad";
        divcenter.style.border = "1px solid #DDDDDD";
        document.body.appendChild(divcenter);
    }

    if (w != null)
        $("divFloatMsg").style.width = w + "px";
    if (h != null)
        $("divFloatMsg").style.height = h + "px";
    if (bgcolor != null)
        $("divFloatMsg").style.background = bgcolor;
    var centerhtml = "<p style='padding:12px 0; text-align:center; margin:0;'>" + html + "</p>";

    $("divFloatMsg").innerHTML = centerhtml;
    var w = $("divFloatMsg").getWidth();
    var h = $("divFloatMsg").getHeight();
    var dh = document.viewport.getHeight(); //可视化区域的高度
    var dw = document.body.offsetWidth || document.documentElement.offsetWidth;
    var sh = document.body.scrollTop || document.documentElement.scrollTop; //已经滚动过的高度
    var th = (parseInt((dh - (h + 22)) / 2) - 30)
    th = th < 30 ? 30 : th;
    th += sh;
    var tw = parseInt((dw - w) / 2) < 0 ? 0 : parseInt((dw - w) / 2)
    $("divFloatMsg").style.top = th + "px";
    $("divFloatMsg").style.left = tw + "px";
}

function CloseFloatMsg(t, func)//关闭提示条
{//如果t是null立即关闭，否则延迟t(毫秒)关闭
    if (t == null) {
        $("divFloatMsg").hide();
        eval(func);
    }
    else {
        window.setTimeout("CloseFloatMsg(null, \"" + func + "\")", t)
    }
}


var FMouseX = null, FMouseY = null; var FMoveable = true; var FMoveStart = false; var ophr = null;
var FSpeed = document.all ? 10 : 20;
function FloatDiv() { }
FloatDiv.prototype = {
    _VersionInfo: "Version:1.3.2;作者: 任维",
    name: "", //属性 命名新实例的名称，例如， var objFloat = new FloatDiv(); objFloat.name="objFloat";
    moveable: true, //属性 是否允许移动
    dialog: true, //属性 是否有模式对话框
    showbg: true, //属性 是否显示半透明背景
    showclose: true, //属性 是否显示关闭的按钮
    mode: "html", //属性 显示内容为html还是frame
    closemode: true, //属性 关闭模式为动画还是直接关闭
    width: "280", //属性 层宽度
    height: "90", //属性 层高度
    title: "", //属性 层标题,null 则不显示头部
    align: "center", //属性 当mode为html时显示内容的水平位置
    valign: "middle", //属性 当mode为html时显示内容的垂直位置
    content: "", //属性 当mode为html时的内容
    url: "", //属性 当mode为frame时的地址链接
    callbackfunc:false,
    shining: function () {//浮动层的标题栏闪烁效果
        var tim = 0;
        var hs = window.setInterval(function () {
            tim++;
            if (tim % 2 == 0)
                $("trShine").className = 'tdc';
            else
                $("trShine").className = 'thc';
            if (tim == 5)
                window.clearInterval(hs);
        }, 80);
    },
    close: function () {//关闭浮动层，带有动画效果, 第一个参数表示是否有返回值，第二个参数表示指定的返回值
        if ($("frmBg") != null) {
            $("frmBg").hide();
        }

        if (this.closemode) {
            var hh = 0;
            var fliter = 100;
            var th = parseInt($("divF").style.top);
            var clhf = window.setInterval(function () {
                fliter -= 10;
                hh += 5;
                $("divF").setStyle({ opacity: (fliter / 100) });
                $("divF").style.top = th - hh + "px";
                if (fliter == 20) {
                    $("frmCenter").src = "";
                    window.clearInterval(clhf);
                    $("divF").hide();
                    $("divF").setStyle({ opacity: 100 });
                }
            }, FSpeed);
        }
        else {
            $("frmCenter").src = "";
            $("divF").hide();
            $("divF").setStyle({ opacity: 100 });
        }
        document.onmousemove = "";
        CloseLoadImg();

        var callback = (arguments.length > 0) ? arguments[0] : false;
        if (callback) {
            //var inpback =window.frames["frmmain"].document.getElementById("txtBack");
        	var inpback =document.getElementById("txtBack");
            if (inpback != null) {
                inpback.value = (arguments.length > 1) ? arguments[1].replace(/<br>/gi, "\n") : ""; //如果指定了返回值，则添加
                inpback.focus(); //为了触发onfocus()事件
                inpback.blur();
            }
        }
        
        if (this.callbackfunc) {
            // var inpback =window.frames["frmmain"].document.getElementById("txtBack");
         	var inpback =document.getElementById("txtBack");
             if (inpback != null) {
                 inpback.value = (arguments.length > 1) ? arguments[1].replace(/<br>/gi, "\n") : ""; //如果指定了返回值，则添加
                 inpback.focus(); //为了触发onfocus()事件
                 inpback.blur();
             }
         }
    },
    open: function () {
        if (ophr != null)
            window.clearInterval(ophr);

        document.onmousemove = "";
        CloseLoadImg();
        FMoveable = this.moveable;

       // var dh = document.body.scrollHeight || document.documentElement.scrollHeight; //整个网页的高度
        var dh=document.documentElement.scrollHeight;
        if (this.dialog) {
            if ($("frmBg") == null) {
                var frm = document.createElement("IFRAME");
                frm.id = "frmBg";
                frm.name = "frmBg";
                frm.frameBorder = 0;
                document.body.appendChild(frm);
                var strHtm = "<html>\r\n";
                strHtm += "<head>\r\n";
                strHtm += "<scr" + "ipt type=\"text/javas" + "cript\">\r\n";
                strHtm += "window.onload = function(){\r\n";
                strHtm += "document.onmousedown = function(){parent.objFloatDiv.shining();}\r\n";
                strHtm += "}\r\n";
                strHtm += "</scr" + "ipt>\r\n";
                strHtm += "</head>\r\n";
                strHtm += "<body oncontextmenu='return false' style='background:#000000'>\r\n";
                strHtm += "</body>\r\n";
                strHtm += "</html>";
                window.frmBg.document.writeln(strHtm);
                window.frmBg.document.close();
            }
            else
                $("frmBg").show();
            $("frmBg").className = this.showbg ? "OpBg" : "OpBgNone";
            $("frmBg").style.height = dh + "px";
        }
        $("tdFme").style.width = this.width + "px";
        if (!this.moveable) {
            $("thFloatTitle").style.cursor = "default";
            $("thFloatCloseTitle").style.cursor = "default";
        }
        else {
            $("thFloatTitle").style.cursor = "";
            $("thFloatCloseTitle").style.cursor = "";
        }

        if (this.title == null) {
            $("trShine").hide();
        }
        else {
            $("spnFloatTitle").innerHTML = this.title;
        }
        if (this.showclose)
            $("divFloatCloseImg").show();
        else
            $("divFloatCloseImg").hide();

        $("tdFloatContent").style.height = this.height + "px";
        if (this.mode == "html") {
            $("tdFloatContent").style.textAlign = this.align;
            $("tdFloatContent").style.verticalAlign = this.valign;
            $("frmCenter").hide();
            $("divFloatContent").show();
            $("divFloatContent").innerHTML = this.content;
            $("divFloatContent").style.height = this.height + "px";
        }
        else if (this.mode == "frame") {
            $("tdFloatContent").style.textAlign = "center";
            $("tdFloatContent").style.verticalAlign = "top";
            $("divFloatContent").hide();
            $("frmCenter").show();
        }

        if (this.mode == "frame")
            LoadImg(false);


        var w = $("divF").getWidth();
        var h = $("divF").getHeight();

        var ch = document.viewport.getHeight(); //可视化区域的高度
        var dw = document.body.offsetWidth || document.documentElement.offsetWidth; //整个网页的宽度
        var sh = document.body.scrollTop || document.documentElement.scrollTop; //已经滚动过的高度
        var th = (parseInt((ch - (h + 22)) / 2) - 20);
        th = th < 10 ? 10 : th;
        th += sh;
        var tw = parseInt((dw - w) / 2) < 0 ? 0 : parseInt((dw - w) / 2)

        $("divF").style.left = tw + "px";


        var dametic = (arguments.length > 0) ? arguments[0] : false;
        if (dametic) {
            var hh = 40;
            var fliter = 20;
            var linkurl = this.url;
            var thismode = this.mode;
            $("divF").style.top = th - hh + "px";
            $("divF").setStyle({ opacity: (fliter / 100) });

            $("divF").show();
            $("divF").focus();

            ophr = window.setInterval(function () {
                fliter += 10;
                hh -= 5;
                $("divF").setStyle({ opacity: (fliter / 100) });
                $("divF").style.top = th - hh + "px";
                if (fliter == 100) {
                    $("divF").setStyle({ opacity: (100) });
                    if (thismode == "frame") {
                        var rnd = Math.random();
                        if (linkurl.include("?")) {
                            var arrPara = linkurl.split("?");
                            $("frmCenter").src = arrPara[0] + "?random=" + rnd + "&" + arrPara[1];
                        }
                        else {
                            $("frmCenter").src = linkurl + "?random=" + rnd;
                        }
                    }
                    window.clearInterval(ophr);
                }
            }, FSpeed);
        }
        else {
            $("divF").style.top = th + "px";
            $("divF").show();
            $("divF").focus();
            if (this.mode == "frame") {
                var rnd = Math.random();
                if (this.url.include("?")) {
                    var arrPara = this.url.split("?");
                    $("frmCenter").src = arrPara[0] + "?random=" + rnd + "&" + arrPara[1];
                }
                else {
                    $("frmCenter").src = this.url + "?random=" + rnd;
                }
            }
        }

        document.onmousemove = function (e) {
            e = window.event || e;
            var LayerLeft = parseInt($("divF").style.left);
            var LayerTop = parseInt($("divF").style.top);
            if (FMouseX == null || FMouseY == null) { return; }
            if (FMoveStart) {
                var ll = e.clientX - FMouseX;
                var tt = e.clientY - FMouseY;
                if (ll <= 0)
                    ll = 0;
                if (tt <= 0)
                    tt = 0;
                if (ll + w >= dw)
                    ll = dw - w;
                if (tt + h >= dh)
                    tt = dh - h;
                $("divF").style.left = ll + "px"; $("divF").style.top = tt + "px"
            }
        }
        $("trShine").onmousedown = function (e) {
            if (FMoveable) { FMoveStart = true; }
            e = window.event || e;
            FMouseX = e.clientX - parseInt($("divF").style.left);
            FMouseY = e.clientY - parseInt($("divF").style.top);
            if (this.setCapture)
                this.setCapture();
            else if (window.captureEvents)
                window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
        }
        $("trShine").onmouseup = function (e) {
            FMoveStart = false;
            if (this.releaseCapture)
                this.releaseCapture();
            else if (window.captureEvents)
                window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
        }
    }
}

//succ是否显示成功，back关闭后是否触发事件，msg失败后作为显示信息，成功时可以不传，也可以作为成功后的返回值
function ShowAlert(succ, back, msg, value, w, h) {
    w = w == null ? "360" : w;
    h = h == null ? "120" : h;
    objFloatDiv = new FloatDiv();
    objFloatDiv.name = "objFloatDiv";
    objFloatDiv.showclose = false;
    objFloatDiv.width = w;
    objFloatDiv.height = h;
    objFloatDiv.title = "系统提示";
    var classN = succ == "0" ? "class='imgWarn imgAlert'" : (succ ? "class='imgWarn imgRight'" : "class='imgWarn imgError'");
    var close = back ? "objFloatDiv.close(true,'" + value + "')" : "objFloatDiv.close()";
    var mess = msg == null ? "<span class='fGreen f14 fBold'>操作成功!</span>" : (succ ? "<span class='fGreen f14 fBold'>" + msg + "</span>" : "<span class='fRed f14 fBold'>" + msg + "</span>");
    var html = "<table border='0' cellspacing='0' cellpadding='4'><tr><td><img border='0' src='"+contextPath+"/Images/no.gif' " + classN + " alt=''/></td><td style='width:260px; white-space:normal' align='left'>" + mess + "</td></tr>";
    html += "<tr><td align='center' colspan='2'><input type='submit' value='确  认' class='btn1' id='btnClose' onclick=\"" + close + ";return false;\"/></td></tr></table>";
    objFloatDiv.content = html;
    objFloatDiv.open();
}
//active代表哪个按钮执行动作，0确认按钮，1取消按钮，2都执行动作(此时value值分0,1,以区分动作)，3不执行任何动作
//msg显示需要确认的信息, succ当操作成功时显示Right.gif，默认Alert.gif, value成功时需要返回的值
function ShowConfirm(active, msg, succ, value, w, h) {
    w = w == null ? "300" : w;
    h = h == null ? "100" : h;
    objFloatDiv = new FloatDiv();
    objFloatDiv.name = "objFloatDiv";
    objFloatDiv.showclose = false;
    objFloatDiv.width = w;
    objFloatDiv.height = h;
    objFloatDiv.title = "系统提示";
    var classN = succ ? "class='imgWarn imgRight'" : "class='imgWarn imgAlert'";
    var close0 = "objFloatDiv.close()";
    var close1 = "objFloatDiv.close()";
    switch (active) {
        case 0:
            close0 = "objFloatDiv.close(true,'" + value + "')";
            break;
        case 1:
            close1 = "objFloatDiv.close(true,'" + value + "')";
            break;
        case 2:
            close0 = "objFloatDiv.close(true,'0')";
            close1 = "objFloatDiv.close(true,'1')";
            break;
        case 3:
            break;
    }
    var html = "<table border='0' cellspacing='0' cellpadding='4'><tr><td><img border='0' src='"+contextPath+"/Images/no.gif' " + classN + " alt=''/></td><td style='width:260px; white-space:normal' align='left'><span class='f14 fBold'>" + msg + "</span></td></tr>";
    html += "<tr><td align='center' colspan='2'><input type='button' value='确认操作' class='btn1' id='btnOK' onclick=\"" + close0 + "\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' value='取消操作' class='btn1' id='btnClose' onclick=\"" + close1 + "\"/></td></tr></table>";
    objFloatDiv.content = html;
    objFloatDiv.open();
}







//消息弹出
var pophr = null;
var popclhr = null;
function PopDiv() { }
PopDiv.prototype = {
    _VersionInfo: "Version:1.3.2;作者: 任维",
    mode: "html", //属性 显示内容为html还是frame
    width: "280", //属性 层宽度
    height: "90", //属性 层高度
    title: "", //属性 层标题
    align: "center", //属性 当mode为html时显示内容的水平位置
    valign: "middle", //属性 当mode为html时显示内容的垂直位置
    content: "", //属性 当mode为html时的内容
    url: "", //属性 当mode为frame时的地址链接
    autoclose: 0, //属性 自动隐藏间隔时间（秒）
    isclose: true, //是否可以执行关闭动作
    close: function () {//关闭浮动层，带有动画效果
        if (this.autoclose > 0 && !this.isclose) {
            window.clearTimeout(popclhr);
            var timer = this.autoclose;
            popclhr = window.setTimeout(function () { objPopDiv.close(); }, timer * 1000);
            return;
        }
        var fliter = 100;
        var clhf = window.setInterval(function () {
            fliter -= 10;
            $("divPop").setStyle({ opacity: (fliter / 100) });
            
            if (fliter == 20) {
                window.clearInterval(clhf);
                if (popclhr != null)
                    window.clearTimeout(popclhr);
                var dh = document.all ? document.body.offsetHeight : document.viewport.getHeight();
         
                $("divPop").style.top = dh + "px";
                $("divPop").setStyle({ opacity: 100 });
                $("divPop").hide();
                $("frmPopCenter").src = "";
            }
        }, FSpeed);
        var callback = (arguments.length > 0) ? arguments[0] : false;
        if (callback) {
            var inpback = window.frames["frmmain"].document.getElementById("txtBack");
            if (inpback != null) {
                inpback.value = (arguments.length > 1) ? arguments[1] : ""; //如果指定了返回值，则添加
                inpback.focus(); //为了触发onfocus()事件
                inpback.blur();
            }
        }
    },
    open: function () {
 
    	
        $("divPop").show();
        if (pophr != null)
            window.clearInterval(pophr);
        if (popclhr != null)
            window.clearTimeout(popclhr);

        $("spnPopTitle").innerHTML = this.title;
        $("tdPop").style.width = this.width + "px";
        $("tdPopContent").style.height = this.height + "px";
  
     	
        if (this.mode == "html") {
          	
            $("tdPopContent").style.textAlign = this.align;
            $("tdPopContent").style.verticalAlign = this.valign;
            $("frmPopCenter").hide();
            $("divPopContent").show();
        
            
            if (!this.content.include("$")) {
            	
                $("divPopContent").innerHTML = this.content;
              
            }else {
            	
                var strHTML = "";
                var strInfo = this.content.split('$');
                if (strInfo.length == 1) {
                	
                    $("divPopContent").innerHTML = this.content;
                }
                else {
                	
                    var poph = parseInt(this.height) - 26;
                    strInfo.each(function (d, index) {
                        var strDisplay = "display:none;";
                        if (index == 0)
                            strDisplay = "";
                        strHTML += "<div id='divPG" + (index + 1) + "' style='height:" + poph + "px; " + strDisplay + "'>" + d + "</div>";
                    });
                    strHTML += "<div style='width:" + this.width + "px; text-align:right'><span onclick='PopPage(false)' class='aspan'>上页</span>&nbsp;<span id='spnPopPageNo'>1</span>/<span id='spnPopPageSize'>" + strInfo.length + "</span>&nbsp;<span onclick='PopPage(true)' class='aspan'>下页</span>&nbsp;</div>";
                    $("divPopContent").innerHTML = strHTML;
                }
            }
        }
        else if (this.mode == "frame") {
            $("tdPopContent").style.textAlign = "center";
            $("tdPopContent").style.verticalAlign = "top";
            $("divPopContent").hide();
            $("frmPopCenter").show();
        }
   
        var w = $("divPop").getWidth() + 6;
        var h = document.all ? $("divPop").getHeight() : (parseInt(this.height) + 32);
        var dh = document.all ? document.body.offsetHeight : document.viewport.getHeight();
        var dw = document.all ? document.body.offsetWidth : document.viewport.getWidth();
        var hh = 0;
      
        $("divPop").style.top = dh + "px";
        $("divPop").style.left = dw - w + "px";
        $("divPop").style.height = "1px";
        var linkurl = this.url;
        var thismode = this.mode;
        pophr = window.setInterval(function () {
            hh += 8;
            $("divPop").style.height = dh - hh + "px";
            if (hh - 6 >= h) {
                $("divPop").style.height = h + "px";
                $("divPop").style.top = dh - h - 6 + "px";
                window.clearInterval(pophr);
                if (thismode == "frame") {
                    var rnd = Math.random();
                    if (linkurl.include("?")) {
                        $("frmPopCenter").src = linkurl + "&random=" + rnd;
                    }
                    else {
                        $("frmPopCenter").src = linkurl + "?random=" + rnd;
                    }
                }

            }
            else {
                $("divPop").style.top = dh - hh + "px";
            }
        }, FSpeed);
        if (document.all)
            $("wave").play();
        if (this.autoclose > 0) {
            var timer = this.autoclose;
            popclhr = window.setTimeout(function () { objPopDiv.close(); }, timer * 1000);
        }
    }
}

function PopPage(d) {
    var pageno = parseInt($("spnPopPageNo").innerHTML);
    if (d) {
        var pagesize = parseInt($("spnPopPageSize").innerHTML);
        if (pageno + 1 <= pagesize) {
            $("divPG" + (pageno + 1)).show();
            $("spnPopPageNo").innerHTML = (pageno + 1);
            $("divPG" + pageno).hide();
        }
    }
    else {
        if (pageno - 1 > 0) {
            $("divPG" + (pageno - 1)).show();
            $("spnPopPageNo").innerHTML = (pageno - 1);
            $("divPG" + pageno).hide();
        }
    }
}

var strPopHtm = "<div id='divPop' class='DivFloat' style='display:none; height:1px;' onmouseover='objPopDiv.isclose=false;' onmouseout='objPopDiv.isclose=true;'><table border='0' cellspacing='0' cellpadding='0' id='tdPop' class='FTable'><tr class='thc'>";
strPopHtm += "<th height='30' align='left'><span id='spnPopTitle'></span><embed src='Images/msg.wav' autostart='false' loop='false' style='display:none' id='wave' /></th><th width='10%'>";
strPopHtm += "<div onclick='objPopDiv.isclose=true;objPopDiv.close()' class='icodin' style='float:right; margin-right:4px;'></div>";
strPopHtm += "</th></tr>";
strPopHtm += "<tr><td id='tdPopContent' colspan='2' align='center' valign='top'><div id='divPopContent' style='line-height:18px;width:280px;white-space:normal;'></div><iframe scrolling='auto' style='border:0px; width:100%;height:100%' frameborder='0' id='frmPopCenter'></iframe></td></tr>";
strPopHtm += "</table></div>";

document.writeln(strPopHtm);
var objPopDiv = new PopDiv();

var strFloatHtm = "<div id='divF' class='DivFloat' style='display:none;'><table border='0' cellspacing='0' cellpadding='0' id='tdFme' class='FTable FTW'>";
strFloatHtm += "<tr class='thc' id='trShine'>";
strFloatHtm += "<th id='thFloatTitle' height='28' align='left'><span id='spnFloatTitle'></span></th><th width='10%' id='thFloatCloseTitle'>";
strFloatHtm += "<div onclick='objFloatDiv.close()' class='icodin' style='float:right; margin-right:4px;' id='divFloatCloseImg'></div>";
strFloatHtm += "</th></tr>";
strFloatHtm += "<tr><td id='tdFloatContent' align='center' colspan='2'><div id='divFloatContent' style='overflow:auto;'></div><iframe scrolling='auto' style='border:0px; width:100%;height:100%' frameborder='0' id='frmCenter' onload='CloseLoadImg()'></iframe></td></tr>";
strFloatHtm += "</table></div>";



document.writeln(strFloatHtm);
var objFloatDiv = new FloatDiv();




var normalelapse = 1000;
var nextelapse = normalelapse;
var counter;
var startTime;
var hrtime = null;
// 开始运行   
function TimeSpanRun() {
    counter = 0;
    // 初始化开始时间   
    startTime = new Date().valueOf();
    // nextelapse是定时时间, 初始时为100毫秒   
    // 注意setInterval函数: 时间逝去nextelapse(毫秒)后, onTimer才开始执行   
    hrtime = window.setInterval("OnTimeSpan()", nextelapse);
}
// 停止运行   
function TimeSpanStop() {
    window.clearTimeout(hrtime);
}
// 倒计时函数   
function OnTimeSpan() {
    var d = $("spnTime");
    var hms = new String(d.innerHTML).split(":");
    if (hms.length == 3) {
        var s = new Number(hms[2]);
        var m = new Number(hms[1]);
        var h = new Number(hms[0]);
        s += 1;
        if (s > 59) {
            s = 00;
            m += 1;
        }
        if (m > 59) {
            m = 00;
            h += 1;
        }
        var ss = s < 10 ? ("0" + s) : s;
        var sm = m < 10 ? ("0" + m) : m;
        var sh = h < 10 ? ("0" + h) : h;
        d.innerHTML = sh + ":" + sm + ":" + ss;
        if (sm > 3 || sh > 0) {
            d.className = "fRed";
        }
    }

    // 清除上一次的定时器   
    window.clearInterval(hrtime);
    // 自校验系统时间得到时间差, 并由此得到下次所启动的新定时器的时间nextelapse   
    counter++;
    var counterSecs = counter * normalelapse;
    var elapseSecs = new Date().valueOf() - startTime;
    var diffSecs = counterSecs - elapseSecs;
    nextelapse = normalelapse + diffSecs;
    if (nextelapse < 0) nextelapse = 0;
    if (counter > 100) {
        counter = 0;
        startTime = new Date().valueOf();
    }
    // 启动新的定时器   
    hrtime = window.setInterval("OnTimeSpan()", nextelapse);
}





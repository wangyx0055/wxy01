function renderSize(value){
    if(null==value||value==''){
        return "0 Bytes";
    }
    var unitArr = new Array("Bytes","KB","MB","GB","TB","PB","EB","ZB","YB");
    var index=0,
        srcsize = parseFloat(value);
 index=Math.floor(Math.log(srcsize)/Math.log(1024));
    var size =srcsize/Math.pow(1024,index);
    //  保留的小数位数
    size=size.toFixed(2);
    return size+unitArr[index];
}

function UpperFirstLetter(str)  
{  
   return str.replace(/\b\w+\b/g, function(word) {  
   return word.substring(0,1).toUpperCase( ) +  word.substring(1);  
 });  
}

function testPort(v){
	return parseInt(v)>0&&parseInt(v)<65535;
}


function _protocol(data) {

    if (data == 1) {
        return "SSH";
    } else if (data == 2) {
        return "RDP";
    } else if (data == 3) {
        return "TELNET";
    } else if (data == 4) {
        return "VNC";
    } else if (data == 5) {
        return "FTP";
    } else if (data == 6) {
        return "SFTP";
    }
}

function command_danger_level(level) {

    if (level == 1) {
        return "允许执行";
    } else if (level == 2) {
        return "动态授权";
    } else if (level == 3) {
        return "拒绝执行";
    } else if (level == 4) {
        return "断开连接";
    } 
}

function stripMilSeconds(data){
	if(data!=null && data!=undefined && data.length>0){
		return data.substring(0,data.indexOf('.'));
	}
	return data;
}

function striftime(data){
	var d = new Date(data.time);
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
}

function _audit_result(data){
	if (data == 1) {
        return "拒绝";
    } else if (data == 2) {
        return "同意";
    }else  {
        return "未审批";
    }
}
const RegExpUtil = (function () {
	function RegExpUtil() {}
	/**
	 * 检查email格式
	 * @param str
	 */
	RegExpUtil.prototype.checkEmail = function (str) {
		const reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
		return this.RegCheck(str, reg);
	};

	/** ipv4**/
	RegExpUtil.prototype.checkIpv4 = function (str) {
		const reg = new RegExp("^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$"); //正则表达式
		return this.RegCheck(str, reg);
	};

	/** ipv6**/
	RegExpUtil.prototype.checkIpv6 = function (str) {
		const reg = new RegExp("^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:)(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$"); //正则表达式
		return this.RegCheck(str, reg);
	};

	/**
	 *检查用户名
	 * 只包含中文、英文、下划线
	 */
	RegExpUtil.prototype.checkName = function (str,length) {
		const reg = new RegExp("^[一-龥A-Za-z0-9_]+$"); //正则表达式
		return this.RegCheckHasLengthLimit(str, reg,length);
	};
	/**
	 * 检查密码， 密码(长度在6~30之间，只能包含字母、数字和下划线)：
	 */
	RegExpUtil.prototype.checkPsw = function (str) {
		const reg = new RegExp("^[A-Za-z0-9_]{6,30}$"); //正则表达式
		return this.RegCheck(str, reg);
	};

	/**
	 * @return {string}
	 * 1.先判断空字符串
	 * 2.判断是不是满足表达式
	 * 3.判断重名
	 */
	RegExpUtil.prototype.RegCheck = function (str, reg) {
		if (str === "") { //输入不能为空
			return "null";
		}
		if (!reg.test(str)) { //正则验证不通过，格式不对
			return "false";
		}
		return "true";
	};
	/**
	 * @return {string}
	 * 1.先判断空字符串
	 * 2.判断在长度范围内是不是满足表达式
	 * 3.判断长度是否超过限制
	 * 4.判断重名
	 */
	RegExpUtil.prototype.RegCheckHasLengthLimit = function (str, reg, length) {
		if (str === "") { //输入不能为空
			return "null";
		}
		if (!reg.test(str) && str.length <= length) { //正则验证不通过，格式不对
			return "false";
		}
		if (str.length > length) { //长度超过限制
			return "length";
		}
		return "true";
	};
	return RegExpUtil;
})();
//调用
const regExpUtil = new RegExpUtil();
/*const reg = "";
if (regExpUtil.checkEmail(reg) ==="null") {
	console.log("输入不能为空");
}else if (regExpUtil.checkEmail(reg) ==="false"){
	console.log("格式错误");
} else {
	console.log("格式正确")
}*/
//带有长度限制
/*
const regLength = "哈56价格低4531_wd";
if (regExpUtil.checkName(regLength,10) ==="null") {
	console.log("输入不能为空");
}else if (regExpUtil.checkName(regLength,10) ==="false"){
	console.log("格式错误");
} else if (regExpUtil.checkName(regLength,10) ==="length"){
	console.log("长度超过限制");
}else {
	console.log("格式正确")
}
*/


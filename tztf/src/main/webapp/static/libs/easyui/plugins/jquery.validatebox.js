/**
 * jQuery EasyUI 1.3.4
 * 
 * Copyright (c) 2009-2013 www.jeasyui.com. All rights reserved.
 *
 * Licensed under the GPL or commercial licenses
 * To use it on other terms please contact us: info@jeasyui.com
 * http://www.gnu.org/licenses/gpl.txt
 * http://www.jeasyui.com/license_commercial.php
 *
 */
(function ($) {
    function _1(_2) {
        $(_2).addClass("validatebox-text");
    };
    function _3(_4) {
        var _5 = $.data(_4, "validatebox");
        _5.validating = false;
        $(_4).tooltip("destroy");
        $(_4).unbind();
        $(_4).remove();
    };
    function _6(_7) {
        var _8 = $(_7);
        var _9 = $.data(_7, "validatebox");
        _8.unbind(".validatebox");
        if (_9.options.novalidate) {
            return;
        }
        _8.bind("focus.validatebox", function () {
            _9.validating = true;
            _9.value = undefined;
            (function () {
                if (_9.validating) {
                    if (_9.value != _8.val()) {
                        _9.value = _8.val();
                        if (_9.timer) {
                            clearTimeout(_9.timer);
                        }
                        _9.timer = setTimeout(function () {
                            $(_7).validatebox("validate");
                        }, _9.options.delay);
                    } else {
                        _f(_7);
                    }
                    setTimeout(arguments.callee, 200);
                }
            })();
        }).bind("blur.validatebox", function () {
            if (_9.timer) {
                clearTimeout(_9.timer);
                _9.timer = undefined;
            }
            _9.validating = false;
            _a(_7);
        }).bind("mouseenter.validatebox", function () {
            if (_8.hasClass("validatebox-invalid")) {
                _b(_7);
            }
        }).bind("mouseleave.validatebox", function () {
            if (!_9.validating) {
                _a(_7);
            }
        });
    };
    function _b(_c) {
        var _d = $.data(_c, "validatebox");
        var _e = _d.options;
        $(_c).tooltip($.extend({}, _e.tipOptions, { content: _d.message, position: _e.tipPosition, deltaX: _e.deltaX })).tooltip("show");
        _d.tip = true;
    };
    function _f(_10) {
        var _11 = $.data(_10, "validatebox");
        if (_11 && _11.tip) {
            $(_10).tooltip("reposition");
        }
    };
    function _a(_12) {
        var _13 = $.data(_12, "validatebox");
        _13.tip = false;
        $(_12).tooltip("hide");
    };
    function _14(_15) {
        var _16 = $.data(_15, "validatebox");
        var _17 = _16.options;
        var box = $(_15);
        var _18 = box.val();
        function _19(msg) {
            _16.message = msg;
        };
        function _1a(_1b) {
            var _1c = /([a-zA-Z_]+)(.*)/.exec(_1b);
            var _1d = _17.rules[_1c[1]];
            if (_1d && _18) {
                var _1e = eval(_1c[2]);
                if (!_1d["validator"](_18, _1e)) {
                    box.addClass("validatebox-invalid");
                    var _1f = _1d["message"];
                    if (_1e) {
                        for (var i = 0; i < _1e.length; i++) {
                            _1f = _1f.replace(new RegExp("\\{" + i + "\\}", "g"), _1e[i]);
                        }
                    }
                    _19(_17.invalidMessage || _1f);
                    if (_16.validating) {
                        _b(_15);
                    }
                    return false;
                }
            }
            return true;
        };
        if (_17.novalidate || box.is(":disabled")) {
            return true;
        }
        if (_17.required) {
            if (_18 == "") {
                box.addClass("validatebox-invalid");
                _19(_17.missingMessage);
                if (_16.validating) {
                    _b(_15);
                }
                return false;
            }
        }
        if (_17.validType) {
            if (typeof _17.validType == "string") {
                if (!_1a(_17.validType)) {
                    return false;
                }
            } else {
                for (var i = 0; i < _17.validType.length; i++) {
                    if (!_1a(_17.validType[i])) {
                        return false;
                    }
                }
            }
        }
        box.removeClass("validatebox-invalid");
        _a(_15);
        return true;
    };
    function _20(_21, _22) {
        var _23 = $.data(_21, "validatebox").options;
        if (_22 != undefined) {
            _23.novalidate = _22;
        }
        if (_23.novalidate) {
            $(_21).removeClass("validatebox-invalid");
            _a(_21);
        }
        _6(_21);
    };
    $.fn.validatebox = function (_24, _25) {
        if (typeof _24 == "string") {
            return $.fn.validatebox.methods[_24](this, _25);
        }
        _24 = _24 || {};
        return this.each(function () {
            var _26 = $.data(this, "validatebox");
            if (_26) {
                $.extend(_26.options, _24);
            } else {
                _1(this);
                $.data(this, "validatebox", { options: $.extend({}, $.fn.validatebox.defaults, $.fn.validatebox.parseOptions(this), _24) });
            }
            _20(this);
            _14(this);
        });
    };
    $.fn.validatebox.methods = {
        options: function (jq) {
            return $.data(jq[0], "validatebox").options;
        }, destroy: function (jq) {
            return jq.each(function () {
                _3(this);
            });
        }, validate: function (jq) {
            return jq.each(function () {
                _14(this);
            });
        }, isValid: function (jq) {
            return _14(jq[0]);
        }, enableValidation: function (jq) {
            return jq.each(function () {
                _20(this, false);
            });
        }, disableValidation: function (jq) {
            return jq.each(function () {
                _20(this, true);
            });
        }
    };
    $.fn.validatebox.parseOptions = function (_27) {
        var t = $(_27);
        return $.extend({}, $.parser.parseOptions(_27, ["validType", "missingMessage", "invalidMessage", "tipPosition", { delay: "number", deltaX: "number" }]), { required: (t.attr("required") ? true : undefined), novalidate: (t.attr("novalidate") != undefined ? true : undefined) });
    };
    $.fn.validatebox.defaults = {
        required: false, validType: null, delay: 200, missingMessage: "This field is required.", invalidMessage: null, tipPosition: "right", deltaX: 0, novalidate: false, tipOptions: {
            showEvent: "none", hideEvent: "none", showDelay: 0, hideDelay: 0, zIndex: "", onShow: function () {
                $(this).tooltip("tip").css({ color: "#000", borderColor: "#CC9933", backgroundColor: "#FFFFCC" });
            }, onHide: function () {
                $(this).tooltip("destroy");
            }
        },
        rules: {
            email: {
                validator: function (_28) {
                    return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(_28);
                }, message: "Please enter a valid email address."
            },
            url: {
                validator: function (_29) {
                    return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(_29);
                }, message: "Please enter a valid URL."
            },
            length: {
                validator: function (_2a, _2b) {
                    var len = $.trim(_2a).length;
                    return len >= _2b[0] && len <= _2b[1];
                }, message: "Please enter a value between {0} and {1}."
            },
            remote: {
                validator: function (_2c, _2d) {
                    var _2e = {};
                    _2e[_2d[1]] = _2c;
                    var _2f = $.ajax({ url: _2d[0], dataType: "json", data: _2e, async: false, cache: false, type: "post" }).responseText;
                    return _2f == "true";
                }, message: "Please fix this field."
            },
            CHS: {
                validator: function (value, param) {
                    return /^[\u0391-\uFFE5]+$/.test(value);
                },
                message: '请输入汉字'
            },
            ZIP: {
                validator: function (value, param) {
                    return /^[1-9]\d{5}$/.test(value);
                },
                message: '邮政编码不存在'
            },
            QQ: {
                validator: function (value, param) {
                    return /^[1-9]\d{4,10}$/.test(value);
                },
                message: 'QQ号码不正确'
            },
            mobile: {
                validator: function (value, param) {
                    return /^1[3|4|5|7|8][0-9]{9}$/.test(value);
                },
                message: '手机号码不正确'
            },
            loginName: {
                validator: function (value, param) {
                    return /^[\u0391-\uFFE5\w]+$/.test(value);
                },
                message: '用户名只允许汉字、英文字母、数字及下划线。'
            },
            safepass: {
                validator: function (value, param) {
                    return safePassword(value);
                },
                message: '密码由字母和数字组成，至少6位'
            },
            equalTo: {
                validator: function (value, param) {
                    return value == $(param[0]).val();
                },
                message: '两次输入的密码不一致'
            },
            number: {
                validator: function (value, param) {
                    return /^\d+$/.test(value);
                },
                message: '请输入数字'
            },
            idcard: {
                validator: function (value, param) {
                    return idCard(value);
                },
                message: '请输入正确的身份证号码'
            },
            PRN: {
                validator: function (value, param) {
                    return /^\d+(\.\d+)?$/.test(value);
                },
                message: '请输入非负实数'
            }
        }
    };
})(jQuery);

/* 密码由字母和数字组成，至少6位 */
var safePassword = function (value) {
    return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
}

var idCard = function (value) {
    if (value.length == 18 && 18 != value.length) return false;
    var number = value.toLowerCase();
    var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
    if (re == null || a.indexOf(re[1]) < 0) return false;
    if (re[2].length == 9) {
        number = number.substr(0, 6) + '19' + number.substr(6);
        d = ['19' + re[4], re[5], re[6]].join('-');
    } else d = [re[9], re[10], re[11]].join('-');
    if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
    for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
    return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
}

var isDateTime = function (format, reObj) {
    format = format || 'yyyy-MM-dd';
    var input = this, o = {}, d = new Date();
    var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
    var len = f1.length, len1 = f3.length;
    if (len != f2.length || len1 != f4.length) return false;
    for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
    for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
    o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
    o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
    o.dd = s(o.dd, o.d, d.getDate(), 31);
    o.hh = s(o.hh, o.h, d.getHours(), 24);
    o.mm = s(o.mm, o.m, d.getMinutes());
    o.ss = s(o.ss, o.s, d.getSeconds());
    o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
    if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
    if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
    d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
    var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
    return reVal && reObj ? d : reVal;
    function s(s1, s2, s3, s4, s5) {
        s4 = s4 || 60, s5 = s5 || 2;
        var reVal = s3;
        if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
        if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
        return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
    }
};
//自定义验证
$.extend($.fn.validatebox.defaults.rules, {
    phoneRex: {
        validator: function (value) {
            var rex = /^1[3-8]+\d{9}$/;
            //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
            //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
            //电话号码：7-8位数字： \d{7,8
            //分机号：一般都是3位数字： \d{3,}
            //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/		 
            //var rex2 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
            //var rex3 = /^((0\d{2,3})-)(\d{7,8})?$/;
            var rex4 = /^((0\d{3}))(\d{7,8})?$/;
            if (rex.test(value) || rex4.test(value)) {
                // alert('t'+value);
                return true;
            } else {
                //alert('false '+value);
                return false;
            }

        },
        message: '请输入正确电话或手机格式'
    }
});
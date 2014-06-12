 var Innloggingslinje = (function() {

   var PREVIOUS_LOGIN_STATE_COOKIE_NAME = "previous_login_state";
   var USERNAME_COOKIE_NAME = "auth_user_name";
   var LOGIN_INFO_SHOWN_COOKIE_NAME = "login.info.hasbeenshown";
   var NOT_LOGGED_IN = "0";
   var SEC_LEVEL_LE_2 = "1";
   var SEC_LEV_GE_3 = "2";

  

   var init = function() {
    var cookieValue = getCookie(PREVIOUS_LOGIN_STATE_COOKIE_NAME);

    switch(cookieValue) {
      case NOT_LOGGED_IN:
      removeClassJS("login", "hidden");
      if (document.getElementById("mainmenu")) {
        removeClassJS("login-mobil", "hidden");
      }
      break;

      case SEC_LEVEL_LE_2:
      document.getElementById("auth-btns").style.display = 'none';
      if (document.getElementById("mainmenu")) {
        document.getElementById("auth-btns-mobil").style.display = 'none';
      }
      break;

      case SEC_LEV_GE_3:
      showLoginDetails(getCookie(USERNAME_COOKIE_NAME));
      break;
    }
  };

  function addClassJS(id, classname) {
    document.getElementById(id).className += " " + classname;
  }

  function removeClassJS(id, classname) {
    var regex = new RegExp('(^|\\s+)' + classname + '(\\s+|$)', 'g');
    if(document.getElementById(id) !== null) {
      document.getElementById(id).className = document.getElementById(id).className.replace(regex, '');
    }
  }

  function showLoginDetails(urlEncodedName) {
    addClassJS("auth-btns", "idporten");    
    removeClassJS("logout", "hidden");
    
    if (document.getElementById("mainmenu")) {  
      removeClassJS("logout-mobil", "hidden");
    }
  
  var name = decodeURI(urlEncodedName);
    addName(name);
    removeClassJS("login-details", "hidden");
  }

  function addName(name) {
    var nameElement = document.getElementById("name");
    nameElement.textContent = name;
    nameElement.innerText = name;
  }


  function showLoginInfoFirstTime() {
    if (!loginInfoHasBeenShown()) {
      setCookie(LOGIN_INFO_SHOWN_COOKIE_NAME, "", thirtyMinutes());
      fadeOutLoginInfo();
    }
  }

  function getCookie(key) {
    var cookies = document.cookie.split(';');
    for (var i=0; i<cookies.length; i++) {
      var cookie = cookies[i];
      var keyValue = cookie.split('=');
      if (keyValue[0].trim() === key) {
        return keyValue[1];
      } 
    }
    return "";
  }

  function setCookie(key, value, expires) {
    document.cookie = key + '=' + value + ';expires=' + expires.toUTCString() + ';domain=.nav.no;path=/;';
  }

  function deleteCookie(key) {
    setCookie(key, "", new Date(0));
  }

  function thirtyMinutes() {
    var date = new Date();
    date.setTime(new Date().getTime() + 30*60*1000);
    return date;
  }
  function setCookieExpiration() {
    setLoginInfoShown(thirtyMinutes());
  }

  function fadeOutLoginInfo() {
    var $tooltip = $('.logout-tooltip');
    $lukk = $tooltip.find('.lukk'); 
    $lukk.removeClass('hidden');
    $tooltip.removeClass('hidden').delay(3000).fadeOut('slow', function() {
      $tooltip.addClass('hidden').show();
      $lukk.addClass('hidden');
    });
  }

  function loginInfoHasBeenShown() {
    return document.cookie.indexOf(LOGIN_INFO_SHOWN_COOKIE_NAME) !== -1;
  }

  function resetAndHideName() {
    var nameElement = document.getElementById("name");
    nameElement.textContent = "";
    nameElement.innerText = "";
  }

  return {
   PREVIOUS_LOGIN_STATE_COOKIE_NAME: PREVIOUS_LOGIN_STATE_COOKIE_NAME,
   USERNAME_COOKIE_NAME: USERNAME_COOKIE_NAME,
   NOT_LOGGED_IN: NOT_LOGGED_IN,
   SEC_LEVEL_LE_2: SEC_LEVEL_LE_2,
   SEC_LEV_GE_3: SEC_LEV_GE_3,
   LOGIN_INFO_SHOWN_COOKIE_NAME: LOGIN_INFO_SHOWN_COOKIE_NAME,

   init:init,
   getCookie:getCookie,
   setCookie:setCookie,
   deleteCookie:deleteCookie,
   showLoginDetails:showLoginDetails,
   showLoginInfoFirstTime:showLoginInfoFirstTime,
   thirtyMinutes:thirtyMinutes,
   setCookieExpiration:setCookieExpiration,
   fadeOutLoginInfo:fadeOutLoginInfo,
   resetAndHideName:resetAndHideName
 };
})();

if(typeof String.prototype.trim !== 'function') {
  String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, ''); 
  }
}
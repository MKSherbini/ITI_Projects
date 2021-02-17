(function (window) {
    // You can enable the strict mode commenting the following line
    // 'use strict';


    // This function will contain all our code
    function myLibrary() {
        var _myLibraryObject = {};

        // This variable will be inaccessible to the user, only visible in the scope of this library.
        var settings = {};


        _myLibraryObject.getCookie = function (id) {
            let docCookies = document.cookie;
            if (docCookies.length === 0) return "";
            let cookies = docCookies.split("; ");
            id = escape(id);

            for (let cookie of cookies) {
                let cookieParts = cookie.split("=");
                let cookieId = cookieParts[0];
                let cookieVal = cookieParts[1];
                cookieVal = unescape(cookieVal);
                if (cookieId === id) {
                    return cookieVal;
                }
            }

            return "";
        };

        _myLibraryObject.setCookie = function (id, val, days) {
            if (arguments.length === 2)
                return _myLibraryObject.setCookie2(id, val);
            else
                return _myLibraryObject.setCookie3(id, val, days);
        };

        _myLibraryObject.setCookie2 = function (id, val) {
            let date = new Date(9999, 9);
            document.cookie = `${escape(id)}=${escape(val)};expires=${date.toDateString()}`;
        };

        _myLibraryObject.setCookie3 = function (id, val, days) {
            let date = new Date(((new Date).getTime()) + (days * 1000 * 60 * 60 * 24));
            document.cookie = `${escape(id)}=${escape(val)};expires=${date.toDateString()}`;
        };

        _myLibraryObject.deleteCookie = function (id) {
            let date = new Date(0, 0);
            document.cookie = `${escape(id)}="";expires=${date.toDateString()}`;
        };

        return _myLibraryObject;
    }

    // We need our library to be globally accessible, so we save it in the window
    if (typeof (window.cookiesManager) === 'undefined') {
        window.cookiesManager = myLibrary();
    }
})(window); // We send the window variable in our function

(function refreshIT() {
    window.location.reload(true);
    setTimeout(refreshIT, 1000);
}());

setInterval(function () {
    window.location.reload(true);
}, 1000);
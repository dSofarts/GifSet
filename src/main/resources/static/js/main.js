function loadGif() {
    if($('#past-curr').text().indexOf('-') == 0) {
        window.location.href = window.location.origin + "/gif/broke"
    } else {
        window.location.href = window.location.origin + "/gif/rich"
    }
}
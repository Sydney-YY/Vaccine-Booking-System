const handleChange = () => {
    if (document.getElementById('confirm-button').style.opacity === '1') {
        document.getElementById('confirm-button').style.opacity='0.5'
        document.getElementById('confirm-button').style.cursor = 'default'
        document.getElementById('confirm-button').disabled = true
    } else {
        document.getElementById('confirm-button').style.opacity='1'
        document.getElementById('confirm-button').style.cursor = 'pointer'
        document.getElementById('confirm-button').disabled = false

    }
}
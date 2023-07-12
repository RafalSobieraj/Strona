    function cancelFormCameras(){
        window.location = "/cameras"
    }

    function cancelFormRecorders(){
        window.location = "/recorders"
    }

    function cancelFormSwitches(){
        window.location = "/switches"
    }

    function cancelForm(){
        window.location = "/"
    }

    function showThumbnail(input){
        file = input.files[0];
        reader = new FileReader();

        reader.onload = function(e){
            $('#thumbnail').attr('src', e.target.result);
        };

        reader.readAsDataURL(file);
}
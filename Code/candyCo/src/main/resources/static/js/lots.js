//Variable flag de estat que determina si es visible o no
var flag = true;

//El parametre e de la funció es un event que recull tota l'informació del boto eliminar
function mostrarPopUp(e) {
    //Obtenim tots els elements que tingin la id amb la string que pasem
    //Aixo o fem per obtenir el Pop-up
    let div = document.getElementById(e.target.name)

    //Oculta i mostra el Pop-Up definit al html canviant el el stat flag i el style
    if (flag == true) {
        div.style.visibility = "visible";
        div.style.opacity = 1;
        flag = false;
    } else {
        div.style.visibility = "hidden";
        div.style.opacity = 0;
        flag = true;
    }
}

//Seteja el valor seleccionar d'un checkbox a un input
function checkedBox(){
    //Busquem amb un query les id box que estiguin amb l'estat checked
    let check = document.querySelector('#box:checked');
    //Busquem amb un query l'input on volem introduir el valor
    let idLot = document.querySelector('#lotidinput');
    //Setejem el valor del checkbox dintre de l'input
    idLot.value = check.value;
}

//Funcio que engloba dues funcions
function iniciarPopUp(e){
    mostrarPopUp(e);
    checkedBox();
}

//Funcio que busca tots els elements seleccionats i els elimina
function deleteAll(){
    let check = document.querySelectorAll('#box:checked');
    if(check == null || check == "" || check == "null"){
        alert("No tens cap camp seleccionat, torna a probar");
    }
    else{
        let input = document.getElementById('deleteinput');
        let array = []
        check.forEach(element => {
            array.push(element.value)
        });
        input.value = array
    }
}
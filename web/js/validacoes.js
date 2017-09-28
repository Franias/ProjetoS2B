/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validarPerfil() {
    var nome = document.getElementById('nome');
    var situacao = document.getElementsByName('situacao');
    
    if (nome.value == '') {
        alert('O campo nome é de preenchimento obrigatório.');
        nome.focus();
        return false;
    } else {
        if (situacao[0].checked == false && situacao[1].checked == false) {
            alert('O campo situação é de preenchimento obrigatório.');
            situacao[0].focus();
            return false;
        }
    }
    
    return true;
}


function validarUsuario() {
    var senha = document.getElementById('senha');
    var confsenha = document.getElementById('confsenha');
    var op = document.getElementById('op');
    
    if (op.value != "ATUALIZAR") {
        if (senha.value == '' || isNaN(senha.value) == true) {
            alert('Senha deve ser preenchida com um valor numérico.');
            senha.value = '';
            senha.focus();
            return false;
        }

        if (senha.value != confsenha.value) {
            alert('Os campos senha e confirmação de senha devem ser idênticos.');
            senha.value = '';
            confsenha.value = '';
            senha.focus();
            return false;
        }
    }
    
    return true;
}

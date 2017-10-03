/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @param {type} ano 
 * @returns {Boolean} valida ano bissexto
 */
function validarAnoBissexto(ano) {
    return ((ano.value % 4 == 0 && ano.value % 100 != 0) || ano.value % 400 == 0);
}

/**
 * @author Eduarda
 * @param dia dia digitado
 * @param mes para array de meses
 * @param ano ano bissexto
 * @return
 */
function validarData(dia, mes, ano) {
    var meses = Array();
    meses = new Array(31,
            validarAnoBissexto(ano) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

    return (dia.value >= 1 && dia.value <= meses[mes.value - 1]);

}

function validarPesquisaSolicitacao() {
    var diaInicial = document.getElementById("diaInicial");
    var mesInicial = document.getElementById("mesInicial");
    var anoInicial = document.getElementById("anoInicial");

    var diaFinal = document.getElementById("diaFinal");
    var mesFinal = document.getElementById("mesFinal");
    var anoFinal = document.getElementById("anoFinal");

    if (!validarData(diaInicial, mesInicial, anoInicial) && !validarData(diaFinal, mesFinal, anoFinal)) {
        alert("Data Inválida!");
        return false;
    } else if (!validarDataSolicitacao(diaInicial, mesInicial, anoInicial) && !validarDataSolicitacao(diaFinal, mesFinal, anoFinal)) {
        aler("Data do documento não deve ser inferior a data corrente da solitação!");
        return false;
    } else
        return true;
}

/**
 * 
 * @returns {Boolean} se foi ou não validado
 */
function validarFormularioSolicitacao() {

    var dia = document.getElementById("dia");
    var mes = document.getElementById("mes");
    var ano = document.getElementById("ano");

    //alert(dia.value + "" + mes.value + "" + ano.value);

    if (!validarData(dia, mes, ano)) {
        alert("Data invalida!");
        return false;

    } else if (!validarDataSolicitacao(dia, mes, ano)) {

        alert("Data do documento "
                + "não deve ser inferior a data corrente da solicitação.");

        return false;

    } else
        return true;

}//fecha método

/**
 * 
 * @param {type} dia dia selecionado
 * @param {type} mes selecionado
 * @param {type} ano selecionado
 * @returns {Boolean|Object} true se ele for maior ou igual a data do sistema
 */
function validarDataSolicitacao(dia, mes, ano) {

    var data = new Date();
    //pega o dia do sistema
    var diaAtual = data.getDate();
    //alert(diaAtual);
    //pega o mês do sistema
    var mesAtual = data.getMonth() + 1;
    //alert(mesAtual);
    //pega ano do sistema
    var anoAtual = data.getFullYear();
    //alert(anoAtual);
    //valida mes menor e dia menor
    var flagMes = (mes.value == mesAtual && dia.value >= diaAtual);
    //alert(flagMes);
    //verifica o ano
    return (ano.value >= anoAtual && (flagMes || mes.value > mesAtual));

}



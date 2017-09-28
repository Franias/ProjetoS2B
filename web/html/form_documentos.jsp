<%-- 
    Document   : form_documentos
    Created on : 29/06/2017, 10:11:09
    Author     : gustavo e cassiano
--%>

<form action="DocumentoOficialControl" method="post">  

    <input type="hidden" name="op" value="pesquisaDocumentosOficiais">

    <label for="chave" class="l_cad">Palavra Chave: </label>
    <input type="text" id="chave" class="q_cad input_texto" name="chave" size="40">
    <!--Período da emissão-->
    <label for="periodoInicial" class="l_cad">Período inicial da emissão: </label>
    <div class="q_cad">

        <select name="anoInicial" id="ano">
            <option value="2017">2017</option> 
        </select>

        <select name="mesInicial" id="mes">
            <option value="01">janeiro</option>
            <option value="02">fevereiro</option>
            <option value="03">março</option>
            <option value="04">abril</option>
            <option value="05">maio</option>
            <option value="06">junho</option>
            <option value="07">julho</option>
            <option value="08">agosto</option>
            <option value="09">setembro</option>
            <option value="10">outubro</option>
            <option value="11">novembro</option>
            <option value="12">dezembro</option>
        </select>

        <select name="diaInicial" id="dia">
            <%for (int i = 1; i <= 31; i++) {%>
            <option value="<%=i%>"><%=i%></option> 
            <%}%>
        </select>

    </div>
    
    <label for="periodoFinal" class="l_cad">Período final da emissão: </label>
    <div class="q_cad">

        <select name="anoFinal" id="anoFinal">
            <option value="2017">2017</option> 
        </select>

        <select name="mesFinal" id="mesFinal">
            <option value="01">janeiro</option>
            <option value="02">fevereiro</option>
            <option value="03">março</option>
            <option value="04">abril</option>
            <option value="05">maio</option>
            <option value="06">junho</option>
            <option value="07">julho</option>
            <option value="08">agosto</option>
            <option value="09">setembro</option>
            <option value="10">outubro</option>
            <option value="11">novembro</option>
            <option value="12" selected>dezembro</option>
        </select>

        <select name="diaFinal" id="diaFinal">
            <%for (int i = 1; i <= 31; i++) {%>
            <option value="<%=i%>"><%=i%></option> 
            <%}%>
        </select>

    </div>        
        
    <label for="tipo" class="l_cad">Tipo: </label>
    
    <div class="q_cad">
        <select name="tipo" id="tipo" class="select" style="width: 150px">
            <option value="">Escolha um Tipo</option>
            <option value="Portaria">Portaria</option>
            <option value="Servico">Ordem de Serviço</option>
            <option value="Resolucao">Resolução</option>
            <option value="Oficio">Ofício</option>
            <option value="Memorando">Memorando</option>
        </select>
    </div>

    <div id="acoes2">
        <input type="submit" value="Pesquisar" class="action">
        <input type="reset" value="Limpar" class="action">
    </div>
</form> 

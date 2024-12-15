$(document).ready(function () {

    $.ajax({
        url: 'http://localhost:8080/api/theme/get',
        method: 'get',
        xhrFields: { withCredentials: true },
        success: function(theme) {
            console.log("Tema recuperado:", theme);
            $('#temaI').text(theme)
            applyTheme(theme); // Aplica o tema obtido
            selectThemeButton(theme); // Seleciona o botão correspondente
        }
    });

    //Carregando o arquivo 'navbar.html' na página
    $('#navbar').load('navbar.html');

    // Get Filme
    $('#formulario').submit(function (event) {
        event.preventDefault();

        if (verificarCampos()) {

            let titulo = $('#tituloInput').val();
            let sinopse = $('#sinopseInput').val();
            let genero = $('#generoInput').val();
            let ano = $('#anoInput').val();

            let filme = {
                titulo: titulo,
                sinopse: sinopse,
                genero: genero,
                ano: ano
            }

            criarFilme(filme);
        }
    });

    $('#formulario-atualizar').submit(function (event) {
        event.preventDefault();

        if (verificarCampos) {

            let id = $('#idInput').val();
            let titulo = $('#tituloInput').val();
            let sinopse = $('#sinopseInput').val();
            let genero = $('#generoInput').val();
            let ano = $('#anoInput').val();

            let filmeAtualizado = {
                id: id,
                titulo: titulo,
                sinopse: sinopse,
                genero: genero,
                ano: ano
            }

            atualizarFilme(filmeAtualizado);
        }
    });

    carregarFilmes();

    function atualizarFilme(filmeAtualizado) {
        $.ajax({
            url: 'http://localhost:8080/filmes/atualizar/' + filmeAtualizado.id,
            method: 'put',
            contentType: 'application/json',
            data: JSON.stringify(filmeAtualizado),
            success: function () {
                alert("Filme atualizado com sucesso.");
                carregarFilmes();
                $('#meuModalAtualizar').modal('hide');
            },
            error: function () {
                alert("Não foi possível atualizar filme.");
            }
        });
    }

    function criarFilme(filme) {
        $.ajax({
            url: 'http://localhost:8080/filmes/adicionar',
            method: 'post',
            contentType: 'application/json',
            data: JSON.stringify(filme),
            success: function () {
                limparCampos();
                carregarFilmes();
            },
            error: function () {
                alert('Não foi possível adicionar o filme à API.');
            }
        });
    }

    function limparCampos() {
        $('#tituloInput').val('');
        $('#sinopseInput').val('');
        $('#generoInput').val('');
        $('#anoInput').val('');
    }

    function verificarCampos() {
        limparErros();

        if (verificarTitulo()) {
            errorTitulo();
            return false;
        }

        if (verificarAno()) {
            errorAno();
            return false;
        }

        if (verificarGenero()) {
            errorGenero();
            return false;
        }

        return true;
    }

    function errorTitulo() {
        // pegando o valor do input e verificando se está vazio
        let titulo = $('#tituloInput').val();
        if (titulo.trim() === '') {
            $('#titulo-error').text('Preecha o campo (Título).');
        }
    }

    function errorAno() {
        let ano = $('#anoInput').val();
        if (ano.trim() === '') {
            $('#ano-error').text('Preencha o campo (Ano de lançamento)');
        }
    }

    function errorGenero() {
        let genero = $('#generoInput').val();
        if (genero.trim() === '') {
            $('#genero-error').text('Preencha o campo (Gênero)');
        }
    }

    // Retornando um boolean verificando se o campo titulo é vazio
    function verificarTitulo() {
        return $('#tituloInput').val().trim() === '';
    }

    function verificarGenero() {
        return $('#generoInput').val().trim() === '';
    }

    function verificarAno() {
        return $('#anoInput').val().trim() === '';
    }

    // limpar todos os campos de erro 
    function limparErros() {
        $('#titulo-error').text('');
        $('#sinopse-error').text('');
        $('#genero-error').text('');
        $('#ano-error').text('');
    }
});

function applyTheme(theme) {
    if (theme === "dark") {
        $('body').addClass('bg-dark').removeClass('bg-light');
        $('label').addClass('label-dark').removeClass('label-light');
        $('p').addClass('p-dark').removeClass('p-light');
    } else {
        $('body').addClass('bg-light').removeClass('bg-dark');
        $('label').addClass('label-light').removeClass('label-dark');
        $('p').addClass('p-light').removeClass('p-dark');
    }
}

function excluirFilme(id) {
    if (confirm("Você tem certeza que quer apagar esse filme?")) {
        $.ajax({
            url: 'http://localhost:8080/filmes/deletar/' + id,
            method: 'delete',
            success: function () {
                alert("Filme excluído com sucesso!");
                carregarFilmes();
            },
            error: function () {
                alert("Não foi possível excluir o filme.");
            }
        });
    }
}

function carregarFilmes() {
    $.ajax({
        url: 'http://localhost:8080/filmes/listar',
        method: 'get',
        dataType: 'json',
        success: function (filmes) {

            $('#tabela tbody').empty();

            filmes.forEach(function (filme) {

                let row = `
                <tr onclick="showSinopse(${filme.id})">
                    <td>${filme.id}</td>
                    <td>${filme.titulo}</td>
                    <td>${filme.genero}</td>
                    <td>${filme.ano}</td>
                    <td>
                        <a class="btn btn-info" data-bs-toggle="modal" data-bs-target="#meuModal" onclick="getFilmes(${filme.id})">Avaliar</a>
                        <a class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#meuModalAtualizar" onclick=" getFilmePorId(${filme.id})">Atualizar</a>
                        <a class="btn btn-danger" onclick="excluirFilme(${filme.id})">Excluir</a>
                    </td>
                </tr>
                `;
                $('#tabela tbody').append(row);
            });
        },
        error: function () {
            alert('Não foi possível carregar a lista de filmes.');
        }
    });
}

function getFilmePorId(id) {
    // Converte a string de volta para um objeto JavaScript, se necessário
    $.ajax({
        url: 'http://localhost:8080/filmes/pesquisar/' + id,
        method: 'get',
        dataType: 'json',
        success: function (filme) {
            // Preenche o campo do título com o valor do objeto `filme`
            $('#idInput').val(filme.id);
            $('#tituloInput').val(filme.titulo);
            $('#sinopseInput').val(filme.sinopse);
            $('#generoInput').val(filme.genero);
            $('#anoInput').val(filme.ano);
        },
        error: function () {
            alert("Não foi possível carregar dados para atualização");
        }
    });
}

function showSinopse(id) {
    $.ajax({
        url: 'http://localhost:8080/filmes/pesquisar/' + id,
        method: 'get',
        dataType: 'json',
        success: function (filme) {
            if (filme.sinopse === '') {
                $('#sinopse').text("Sem Sinopse.");
            } else {
                $('#sinopse').text(filme.sinopse);
            }
        }
    });

    showAvaliacoes(id);
}

function showAvaliacoes(id) {
    $.ajax({
        url: 'http://localhost:8080/filmes/' + id + '/analises',
        method: 'get',
        dataType: 'json',
        success: function (analises) {

            $('#listaAnalises').empty();
            if (analises.length === 0) {
                $('#listaAnalises').append(`<li class="list-group-item">Não há avaliações para esse filme</li>`);
            } else {

            }
            analises.forEach(function (analise) {
                let row = `
                    <div class="mb-3 d-flex justify-content-between align-items-center">
                        <li class="list-group-item border-0 p-0">Nota: <strong>${analise.nota}</strong> - ${analise.analise}</li>
                        <a class="btn btn-outline-danger" onclick="excluirAnalise(${id}, ${analise.id})">Excluir</a>
                    </div>
                `;
                $('#listaAnalises').append(row);
            });
        }
    });
}

function excluirAnalise(idFilme, idAnalise){
    $.ajax({
        url: 'http://localhost:8080/filmes/'+idFilme+'/analises/'+idAnalise,
        method: 'delete',
        success: function(){
            alert("Análise deletada com sucesso!");
            showAvaliacoes(idFilme);
        },
        error: function(){
            alert("Não foi possível deletar análise!");
        }
    });
}

function getFilmes(id) {
    $.ajax({
        url: 'http://localhost:8080/filmes/pesquisar/' +id,
        method: 'get',
        dataType: 'json',
        success: function (filme) {
            $('#selectAvaliar').empty();
            let option = `
                <option id="${filme.id}">${filme.titulo}</option>
            `;
            $('#selectAvaliar').append(option);
        }
    });
}

$('#formulario2').submit(function (event) {
    event.preventDefault();

    let analiseAtributo = $('#analiseArea').val();
    let nota = $('#notaInput').val();
    let id = $('#selectAvaliar option:selected').attr('id');

    let analise = {
        analise: analiseAtributo,
        nota: nota
    }

    $('#analiseArea').val('');
    $('#notaInput').val('');

    criarAnalise(id, analise);
    $('#meuModal').modal('hide');
    showSinopse(id);
})

function criarAnalise(id, analise) {
    $.ajax({
        url: 'http://localhost:8080/filmes/' + id + '/analises',
        method: 'post',
        contentType: 'application/json',
        data: JSON.stringify(analise),
        success: function () {
            console.log("Análise cadastrada com sucesso!");
            showAvaliacoes(id);
        },
        error: function () {
            alert("Não foi possível cadastrar análise");
        }
    });
}

function setTheme(theme) {
    $.ajax({
        url: 'http://localhost:8080/api/theme/set',
        method: 'post',
        data: { theme: theme },
        xhrFields: { withCredentials: true },
        success: function() {
            console.log("Tema definido como: " + theme);
            $('#temaI').text(theme)
            console.log(theme);
            applyTheme(theme);
        }
    });
}

function selectThemeButton(theme) {
    if (theme === "dark") {
        $('#option2').prop('checked', true);
    } else {
        $('#option1').prop('checked', true);
    }
}
const API_URL = '/api/abastecimentos';
const BOMBAS_URL = '/api/bombas';
const COMBUSTIVEIS_URL = '/api/combustiveis';

let bombas = [];
let combustiveis = [];

document.addEventListener('DOMContentLoaded', () => {
    carregarDados();
    document.getElementById('formAbastecimento').addEventListener('submit', registrar);
    document.getElementById('bombaId').addEventListener('change', calcularValor);
    document.getElementById('litros').addEventListener('input', calcularValor);
});

async function carregarDados() {
    const [bombasRes, combustiveisRes] = await Promise.all([
        fetch(BOMBAS_URL),
        fetch(COMBUSTIVEIS_URL)
    ]);

    bombas = await bombasRes.json();
    combustiveis = await combustiveisRes.json();

    const select = document.getElementById('bombaId');
    bombas.forEach(b => {
        select.innerHTML += `<option value="${b.id}" data-combustivel="${b.tipoCombustivelId}">${b.nome}</option>`;
    });
}

function calcularValor() {
    const bombaSelect = document.getElementById('bombaId');
    const litros = parseFloat(document.getElementById('litros').value);

    if (!bombaSelect.value || !litros) return;

    const bomba = bombas.find(b => b.id == bombaSelect.value);
    const combustivel = combustiveis.find(c => c.id == bomba.tipoCombustivelId);

    if (combustivel) {
        const valorTotal = litros * combustivel.precoLitro;
        document.getElementById('valorTotal').value = valorTotal.toFixed(2);
    }
}

async function registrar(event) {
    event.preventDefault();

    const litros = parseFloat(document.getElementById('litros').value);
    const valorTotal = parseFloat(document.getElementById('valorTotal').value);
    const precoLitroPraticado = valorTotal / litros;

    const agora = new Date();
    const dataFormatada = agora.toISOString().slice(0, 19);

    const dados = {
        bombaId: parseInt(document.getElementById('bombaId').value),
        litros: litros,
        valorTotal: valorTotal,
        precoLitroPraticado: precoLitroPraticado,
        dataAbastecimento: dataFormatada
    };

    const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(dados)
    });

    if (response.ok) {
        alert('Abastecimento registrado!');
        document.getElementById('formAbastecimento').reset();
    } else {
        const erro = await response.text();
        alert('Erro ao registrar: ' + erro);
    }
}

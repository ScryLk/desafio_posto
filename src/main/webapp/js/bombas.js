const API_URL = '/api/bombas';
const COMBUSTIVEIS_URL = '/api/combustiveis';

let combustiveis = [];

document.addEventListener('DOMContentLoaded', () => {
    carregarCombustiveis();
    carregarBombas();
    document.getElementById('formBomba').addEventListener('submit', salvarBomba);
});

async function carregarCombustiveis() {
    const response = await fetch(COMBUSTIVEIS_URL);
    combustiveis = await response.json();

    const select = document.getElementById('tipoCombustivelId');
    select.innerHTML = '<option value="">Selecione...</option>';

    combustiveis.forEach(c => {
        select.innerHTML += `<option value="${c.id}">${c.nome}</option>`;
    });
}

async function carregarBombas() {
    const response = await fetch(API_URL);
    const bombas = await response.json();

    const tbody = document.querySelector('#tabelaBombas tbody');
    tbody.innerHTML = '';

    bombas.forEach(bomba => {
        const combustivel = combustiveis.find(c => c.id === bomba.tipoCombustivelId);
        tbody.innerHTML += `
            <tr>
                <td>${bomba.id}</td>
                <td>${bomba.nome}</td>
                <td>${combustivel ? combustivel.nome : ''}</td>
                <td>
                    <button class="btn" onclick="editarBomba(${bomba.id})">Editar</button>
                    <button class="btn" onclick="excluirBomba(${bomba.id})">Excluir</button>
                </td>
            </tr>
        `;
    });
}

async function salvarBomba(event) {
    event.preventDefault();

    const id = document.getElementById('bombaId').value;
    const dados = {
        nome: document.getElementById('nome').value,
        tipoCombustivelId: parseInt(document.getElementById('tipoCombustivelId').value)
    };

    const url = id ? `${API_URL}/${id}` : API_URL;
    const method = id ? 'PUT' : 'POST';

    await fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(dados)
    });

    alert(id ? 'Bomba atualizada!' : 'Bomba cadastrada!');
    limparForm();
    carregarBombas();
}

async function editarBomba(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const bomba = await response.json();

    document.getElementById('bombaId').value = bomba.id;
    document.getElementById('nome').value = bomba.nome;
    document.getElementById('tipoCombustivelId').value = bomba.tipoCombustivelId;
}

async function excluirBomba(id) {
    if (!confirm('Deseja realmente excluir esta bomba?')) return;

    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    alert('Bomba excluída!');
    carregarBombas();
}

function limparForm() {
    document.getElementById('bombaId').value = '';
    document.getElementById('nome').value = '';
    document.getElementById('tipoCombustivelId').value = '';
}

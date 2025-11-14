const API_URL = '/api/combustiveis';

// Carregar combustíveis ao carregar a página
document.addEventListener('DOMContentLoaded', () => {
    carregarCombustiveis();

    document.getElementById('formCombustivel').addEventListener('submit', salvarCombustivel);
});

async function carregarCombustiveis() {
    try {
        const response = await fetch(API_URL);
        const combustiveis = await response.json();

        const tbody = document.querySelector('#tabelaCombustiveis tbody');
        tbody.innerHTML = '';

        combustiveis.forEach(combustivel => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${combustivel.id}</td>
                <td>${combustivel.nome}</td>
                <td>R$ ${combustivel.precoLitro.toFixed(2)}</td>
                <td>
                    <button class="btn btn-warning" onclick="editarCombustivel(${combustivel.id})">Editar</button>
                    <button class="btn btn-danger" onclick="excluirCombustivel(${combustivel.id})">Excluir</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Erro ao carregar combustíveis:', error);
        alert('Erro ao carregar combustíveis');
    }
}

async function salvarCombustivel(event) {
    event.preventDefault();

    const id = document.getElementById('combustivelId').value;
    const dados = {
        nome: document.getElementById('nome').value,
        precoLitro: parseFloat(document.getElementById('precoLitro').value)
    };

    try {
        const url = id ? `${API_URL}/${id}` : API_URL;
        const method = id ? 'PUT' : 'POST';

        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dados)
        });

        if (response.ok) {
            alert(id ? 'Combustível atualizado!' : 'Combustível cadastrado!');
            limparForm();
            carregarCombustiveis();
        }
    } catch (error) {
        console.error('Erro ao salvar:', error);
        alert('Erro ao salvar combustível');
    }
}

async function editarCombustivel(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const combustivel = await response.json();

        document.getElementById('combustivelId').value = combustivel.id;
        document.getElementById('nome').value = combustivel.nome;
        document.getElementById('precoLitro').value = combustivel.precoLitro;
    } catch (error) {
        console.error('Erro ao carregar combustível:', error);
    }
}

async function excluirCombustivel(id) {
    if (!confirm('Deseja realmente excluir este combustível?')) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });

        if (response.ok) {
            alert('Combustível excluído!');
            carregarCombustiveis();
        }
    } catch (error) {
        console.error('Erro ao excluir:', error);
        alert('Erro ao excluir combustível');
    }
}

function limparForm() {
    document.getElementById('combustivelId').value = '';
    document.getElementById('nome').value = '';
    document.getElementById('precoLitro').value = '';
}


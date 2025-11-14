const API_URL = '/api/abastecimentos';
const BOMBAS_URL = '/api/bombas';

let bombas = [];

document.addEventListener('DOMContentLoaded', () => {
    carregarDados();
});

async function carregarDados() {
    const [abastecimentosRes, bombasRes] = await Promise.all([
        fetch(API_URL),
        fetch(BOMBAS_URL)
    ]);

    const abastecimentos = await abastecimentosRes.json();
    bombas = await bombasRes.json();

    const tbody = document.querySelector('#tabelaAbastecimentos tbody');
    tbody.innerHTML = '';

    abastecimentos.forEach(abast => {
        const bomba = bombas.find(b => b.id === abast.bombaId);

        let dataFormatada = 'N/A';
        if (abast.dataAbastecimento) {
            if (Array.isArray(abast.dataAbastecimento)) {
                const [ano, mes, dia, hora, minuto, segundo] = abast.dataAbastecimento;
                dataFormatada = new Date(ano, mes - 1, dia, hora, minuto, segundo).toLocaleString('pt-BR');
            } else if (typeof abast.dataAbastecimento === 'string') {
                const data = abast.dataAbastecimento.replace(' ', 'T');
                dataFormatada = new Date(data).toLocaleString('pt-BR');
            }
        }

        tbody.innerHTML += `
            <tr>
                <td>${abast.id}</td>
                <td>${bomba ? bomba.nome : ''}</td>
                <td>${dataFormatada}</td>
                <td>${abast.litros.toFixed(2)}</td>
                <td>R$ ${abast.valorTotal.toFixed(2)}</td>
                <td>R$ ${abast.precoLitroPraticado.toFixed(2)}</td>
            </tr>
        `;
    });
}

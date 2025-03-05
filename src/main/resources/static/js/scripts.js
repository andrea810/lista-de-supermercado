// Função para marcar item como comprado
function marcarComoComprado(listaId, itemId) {
  const checkbox = event.target;
  const url = `/listas/${listaId}/itens/${itemId}/comprado`;

  fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        checkbox.checked = !checkbox.checked;
        alert("Erro ao atualizar o item");
      }
    })
    .catch((error) => {
      checkbox.checked = !checkbox.checked;
      console.error("Erro:", error);
    });
}

// Função para confirmar exclusão de item
function confirmarExclusao(listaId, itemId) {
    if (confirm("Tem certeza que deseja excluir este item?")) {
        window.location.href = `http://localhost:8081/listas/${listaId}/itens/${itemId}/delete`;
    }
}

// Remove the duplicate marcarComoComprado function and update the existing one
function marcarComoComprado(listaId, itemId) {
    fetch(`/listas/${listaId}/itens/${itemId}/comprado`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao marcar item');
        }
        window.location.reload();
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao marcar o item como comprado');
    });
}

// Função para exportar lista em PDF
function exportarPDF(listaId) {
    window.open(`/listas/${listaId}/pdf`, "_blank");
}

// Validação do formulário de adição de item
document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");
  if (form) {
    form.addEventListener("submit", function (event) {
      const descricao = form.querySelector('[name="descricao"]').value;
      const quantidade = form.querySelector('[name="quantidade"]').value;

      if (descricao.trim().length === 0 || quantidade < 1) {
        event.preventDefault();
        alert("Por favor, preencha todos os campos corretamente");
      }
    });
  }
});

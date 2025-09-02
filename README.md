# Lista de Supermercado 🛒

Uma aplicação web simples e intuitiva para gerenciar suas listas de compras de supermercado.

## 📋 Sobre o Projeto

Este é um projeto desenvolvido em **Spring Boot** que permite criar e gerenciar listas de compras de forma prática. Você pode adicionar itens, organizá-los por categoria, definir quantidades e marcar como comprados durante suas compras.

## ✨ Funcionalidades

- ✅ Criar e gerenciar listas de compras
- ➕ Adicionar itens com descrição e quantidade
- 🏷️ Organizar itens por categorias (Frutas, Verduras, Carnes, etc.)
- ✏️ Editar quantidade e categoria dos itens
- ☑️ Marcar itens como comprados
- 🗑️ Remover itens da lista
- 📱 Interface responsiva e amigável
- 🖨️ Visualização otimizada para impressão

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Web MVC**
- **Spring Data JPA**
- **Thymeleaf** (Template Engine)
- **H2 Database** (Banco de dados em memória)
- **Bootstrap** (Framework CSS)
- **Maven** (Gerenciador de dependências)

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven (ou use o wrapper incluído)

### Passos para execução

1. **Clone o repositório**

   ```bash
   git clone <url-do-repositorio>
   cd lista-de-supermercado
   ```

2. **Execute a aplicação**

   ```bash
   ./mvnw spring-boot:run
   ```

   Ou no Windows:

   ```cmd
   mvnw.cmd spring-boot:run
   ```

3. **Acesse a aplicação**

   Abra seu navegador e acesse: `http://localhost:3000`

## 📖 Como Usar

1. **Página Inicial**: Visualize suas listas existentes
2. **Nova Lista**: Clique em "Nova Lista" para criar uma lista
3. **Adicionar Itens**: Use o formulário para adicionar itens à sua lista
4. **Gerenciar Itens**:
   - Clique na quantidade para editá-la
   - Clique na categoria para alterá-la
   - Use o checkbox para marcar como comprado
   - Use o botão de lixeira para remover itens
5. **Imprimir**: Use o botão "Imprimir" para uma versão otimizada para papel

## 🗂️ Estrutura do Projeto

```
lista-de-supermercado/
├── .gitattributes
├── .gitignore
├── .mvn/wrapper/
├── README.md
├── lista-de-supermercado.code-workspace
├── mvnw & mvnw.cmd
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/supermarket/lista_de_supermercado/
    │   └── resources/
    │       ├── static/
    │       └── templates/
    └── test/
        └── java/com/supermarket/lista_de_supermercado/
```

## 💾 Banco de Dados

O projeto utiliza o **H2 Database** em memória, que é automaticamente configurado e populado na inicialização. Os dados são perdidos quando a aplicação é encerrada, tornando-o ideal para desenvolvimento e testes.

## 🤝 Contribuindo

Este é um projeto simples, mas contribuições são bem-vindas! Sinta-se à vontade para:

- Reportar bugs
- Sugerir melhorias
- Enviar pull requests

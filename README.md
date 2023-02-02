# Exemplo de Spring Boot



# Funcionalidades

 - Criar uma pessoa
 - Editar uma pessoa
 - Consultar uma pessoa
 - Listar pessoas
 - Criar endereço para pessoa
 - Listar endereços da pessoa
 - Poder informar qual endereço é o principal da pessoa



## Mapeamentos


All your files and folders are presented as a tree in the file explorer. You can switch from one to another by clicking a file in the tree.

### Criar uma pessoa

> Post: localhost:8080/pessoa
Criando uma pessoa

    {
	    "nome":"João Silva",
	    "dataNascimento":"1990-12-21"
	}

Também é possivel adicionar pessoas com seus respectivos endereços.

    {	
	    "nome":"João Silva",
		"dataNascimento":"1990-12-21",
		"enderecos":[
			{"logradouro": "Rua Maria Silva",
			"numero": "100",
			"cidade": "Rio de Janeiro",
			"cep": "00000-000",
			"uf": "RJ"},
			{"logradouro": "Rua João Santos",
			"numero": "20",
			"cidade": "Recife",
			"cep": "11111-111",
			"uf": "PE"},
			{"logradouro": "Rua Pedro Sá",
			"numero": "85",
			"cidade": "Manaus",
			"cep": "33333-333",
			"uf": "AM"}
			]
	}


### Editar uma pessoa
> Put: localhost:8080/pessoa/**{id}**

Deve  subistituir o {id} pelo número do id da pessoa que deseja alterar.

    {"nome":"João Silva dos Santos", "dataNascimento":"1990-12-21"}
    
### Consultar uma pessoa
> Get: localhost:8080/pessoa/**{id}**

Deve  subistituir o {id} pelo número do id da pessoa que deseja buscar. Caso o Id não exista, retorna uma mensagem informando que não foi possível encontrar a pessoa com o id informado.

### Listar pessoas
> Get: localhost:8080/pessoa

### Criar endereço para pessoa

> Post: localhost:8080/endereco/**{idPessoa}**

    {
    "logradouro":"Rua da Paz",
	"numero":"100",
	"cep":"56800-000",
	"cidade":"Porto Alegre",
	"uf":"RS",
	"pessoa_id":1
	}



### Listar endereços da pessoa

> GET: localhost:8080/endereco/pessoa/**{idPessoa}**



### Listar endereços da pessoa

> GET: localhost:8080/endereco/principal/**{idEndereco}**/pessoa/**{idPessoa}**




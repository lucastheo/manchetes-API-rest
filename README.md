# Manchetes-API-rest

Manchetes-API-rest é uma aplicação em java com springboot, tem como finalidade disponibilizar em API rest os dados feitos pelo repositório manchetes-data, ou seja, retornar os diversos json refined.

# Rotas
#### data/query/most_cited_keys
* Retornar a informação de dadas e urls disponíveis no sistema
* Argumentos: data e url, são booleans quando ativados retornam as chaves ou por url ou data ou ambos

#### data/query/most_cited_by
* Retorna as palavras com maiores frequencias
* Argumentos: data e url, são string que quando passadas especificam qual a busca que vai ser ultizada (utlizar data/query/most_cited_keys para saber as chaves)
# OpenAcad Service API

API RESTful projetada para ser o *core backend* de uma plataforma de gestão acadêmica/educacional. O sistema gerencia o ciclo de vida de alunos, turmas, professores e o processo de matrículas, garantindo a consistência dos dados e a aplicação estrita das regras de negócio da instituição.

Este projeto foi construído priorizando um design limpo, escalabilidade e isolamento de domínio, servindo como uma demonstração prática de engenharia de software aplicada a sistemas corporativos.

## 🏗️ Arquitetura e Decisões de Design

O projeto adota uma **Arquitetura em Camadas (Layered Architecture)** com separação rigorosa de responsabilidades, garantindo que o modelo de persistência não vaze para as bordas da aplicação e que a lógica de negócios permaneça agnóstica a detalhes de infraestrutura HTTP.

### 1. Contratos da API e Proteção de Fronteira (Controllers & DTOs)
Os Controllers são mantidos extremamente "magros", atuando apenas como roteadores HTTP e gerenciadores de status code.
- **Isolamento com DTOs:** Entidades do banco de dados (Entities) nunca cruzam a fronteira da API. O uso de `RequestDTO` e `ResponseDTO` previne vulnerabilidades como *Mass Assignment* (Over-Posting) e permite que a estrutura do banco de dados evolua sem quebrar o contrato estabelecido com os clientes (Front-end/Mobile).
- **Fail-Fast com Bean Validation:** A validação sintática (ex: formato de e-mail, campos obrigatórios, datas inválidas) ocorre na borda (via Jakarta Validation) antes mesmo de a requisição atingir a camada de negócios, economizando processamento.

### 2. Conversão e Mapeamento (Mappers)
Para garantir a transição de estados entre as camadas sem poluir o código, o sistema implementa o padrão de **Mappers**.
- A lógica de tradução de `Entity -> DTO` e vice-versa fica contida em classes dedicadas. Isso garante o Princípio de Responsabilidade Única (SRP), tornando o código da Service muito mais focado na regra de negócio e os mapeamentos facilmente testáveis de forma unitária.

### 3. Integração com Ecossistema Externo (Spring Cloud OpenFeign)
A comunicação com microsserviços ou APIs de terceiros (ex: serviços de mensageria, validação de documentos de identidade ou consulta de CEP) é feita de forma declarativa.
- **Proxy HTTP via OpenFeign:** A abstração do Feign elimina a complexidade e o boilerplate de clientes HTTP tradicionais. Ao mapear integrações como interfaces, o código torna-se muito mais limpo e já preparado para a adoção de padrões de resiliência, como *Circuit Breakers* e *Fallbacks*, em cenários de degradação de serviços externos.

### 4. O Coração do Sistema (Camada de Service)
A camada de `Service` concentra a inteligência do domínio, orquestrando fluxos complexos e garantindo as invariantes do negócio acadêmico.
- **Gestão Transacional (ACID):** Operações críticas, como a efetivação de uma matrícula, são anotadas com `@Transactional`. Se um aluno for inserido na turma, mas a notificação/faturamento falhar, o estado da aplicação sofre *rollback* automático, evitando dados inconsistentes (órfãos) no banco de dados.
- **Validação de Invariantes Complexas:** O sistema impede operações sistemicamente inválidas. Por exemplo, a Service garante que uma matrícula não seja processada se a turma já atingiu sua capacidade máxima ou se o aluno não possui os pré-requisitos necessários, lançando exceções de domínio claras.
- **Máquina de Estados de Matrícula:** O ciclo de vida de uma matrícula (`PENDENTE` -> `ATIVA` -> `CONCLUIDA` / `CANCELADA`) é protegido. Transições arbitrárias são bloqueadas, garantindo que o histórico do aluno reflita a realidade.

## 💻 Stack Tecnológica

- **Java 17+**
- **Spring Boot 3** (Web, Data JPA, Validation)
- **Spring Cloud OpenFeign:** Integração HTTP declarativa.
- **Mapeamento:** Padrão Mapper para separação estrita DTO/Entity.
- **Persistência:** PostgreSQL via Hibernate/JPA.
- **Tratamento de Erros:** `@RestControllerAdvice` aplicando a RFC 7807 (Problem Details for HTTP APIs), padronizando payloads de erro para facilitar a vida dos consumidores da API.

## 🚀 Instruções de Execução

### Pré-requisitos
- JDK 17+
- Maven 3.8+
- Docker & Docker Compose (Para provisionamento da infraestrutura de dados)

### Subindo o Ambiente Local

1. Clone o repositório para sua máquina:
```bash
git clone [https://github.com/PedrodeAndradecf/ProjetoBckOpenAcad2026.git](https://github.com/PedrodeAndradecf/ProjetoBckOpenAcad2026.git)
cd ProjetoBckOpenAcad2026

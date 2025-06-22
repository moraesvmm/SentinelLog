(Este projeto está em desenvolvimento ativo, com foco em aprendizado técnico avançado de Java, arquitetura de aplicações desktop e segurança cibernética. 
A estrutura foi pensada para escalar e permitir a adição progressiva de funcionalidades com manutenibilidade.)


# SentinelLog

SentinelLog é uma aplicação Java desenvolvida com JavaFX 21 para análise de logs SSH, com foco em segurança da informação. O objetivo é automatizar e facilitar a visualização de tentativas de autenticação em servidores Linux, destacando padrões de comportamento suspeitos.

## Motivação

Durante os estudos em cibersegurança e desenvolvimento backend em Java, identificamos a necessidade de uma ferramenta leve, de uso local, que pudesse ajudar na triagem inicial de eventos registrados em `/var/log/auth.log` e similares. O SentinelLog foi idealizado como um projeto educacional com viés profissional, com as seguintes premissas:

- Utilizar **Java moderno (versão 21)** e **JavaFX 21** para GUI;
- Aplicar **boas práticas de programação orientada a objetos**;
- Organizar o projeto de forma modular, com pacotes específicos por responsabilidade (visão, controle, modelo);
- Adicionar **estilo visual personalizado (CSS)** para tornar a interface mais clara e apresentável;
- Preparar o repositório com potencial de extensão futura (ex: exportação de relatórios, integração remota via SSH, etc).

## Funcionalidades

- Leitura de arquivos de log SSH (`auth.log`)
- Extração de IPs, horários e mensagens relevantes com regex
- Visualização gráfica e sumarização dos dados extraídos (em construção)
- Interface gráfica simples, porém estilizada, com botões de ação
- Suporte modularizado para adição futura de tabelas, filtros e gráficos

## Tecnologias utilizadas

- Java 21 (Temurin)
- JavaFX 21.0.7 (SDK externo)
- JavaFX CSS
- IntelliJ IDEA CE
- Regex (expressões regulares para parsing de logs)
- Git e GitHub para versionamento e colaboração


## Design e arquitetura

- **MVC simplificado**: O projeto adota uma separação básica entre model, controller e view, facilitando legibilidade e manutenção.
- **JavaFX puro**: A interface foi feita sem FXML, priorizando construção direta via código Java, para maior controle e aprendizado dos elementos visuais.
- **Regex no parser**: As expressões regulares foram desenhadas para extrair IPs e mensagens específicas com alta precisão.
- **Recursos externos separados**: Estilos visuais são definidos em `style.css` localizado dentro da pasta `resources/`, marcada como `Resources Root`.

## Execução

Pré-requisitos:
- Java 21 instalado
- JavaFX SDK 21 baixado e descompactado
- IntelliJ configurado corretamente com `VM options`

Etapas:
1. Configure o caminho do JavaFX no IntelliJ:
   - File > Project Structure > Libraries > Add JavaFX SDK
2. Marque a pasta `resources` como `Resources Root` (clicando com botão direito sobre ela)
3. Execute com os seguintes parâmetros de VM:

```bash
--module-path /caminho/para/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml

<img width="805" alt="SentinelLog" src="https://github.com/user-attachments/assets/211d5b19-c149-4ea5-b2dd-c539b28de4ac" />



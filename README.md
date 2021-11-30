# Programação Web II - SOAP
 
### Observações:
- Usar JDK 11


- O projeto é feito usando Kotlin, se preferir, podem criar classes Java e usar elas no Kotlin ou usar as classes 
 Kotlin no Java como se fosse outro código normal (são compatíveis)


- Ao alterar o arquivo domain.xml, no IntelliJ, abra a aba **Maven** -> **ProgWebII-Soap** -> **Plugins** ->
**jaxb2** -> **jaxb2:xjc**


- Caso criar outro arquivo .xsd, editar no arquivo pom.xml e adicionar uma nova tag source dentro de configuration 
do plugin jaxb2-maven-plugin (final do arquivo)
-----

## HotReload/HotSwap
No IntelliJ:
 - Vá até **Settings** (Ctrl + Shift + S) -> **Build, Execution, Deployment** -> **Compiler** e ative: **Build Project Automatically.**
 - Depois disso, vá até **Advanced Settings** e ative **Allow auto-make to start even if developed application is currently running**
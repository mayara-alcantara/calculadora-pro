# 🧮 Calculadora Pro — Java Swing

Uma calculadora desktop com interface gráfica moderna desenvolvida em **Java** utilizando a biblioteca **Swing**.

![Java](https://img.shields.io/badge/Java-8%2B-orange?style=for-the-badge&logo=java)
![Swing](https://img.shields.io/badge/UI-Swing-blue?style=for-the-badge)

---

## ✨ Funcionalidades

- ➕ Operações básicas: soma, subtração, multiplicação e divisão
- 📐 **Modo Científico** com 18 funções:
  - Trigonometria: `sin`, `cos`, `tan` e suas inversas
  - Logaritmos: `log`, `ln`
  - Potências e raízes: `x²`, `xʸ`, `√`, `10^x`, `e^x`
  - Extras: `1/x`, `n!`, `|x|`, `π`, `e`
- ⏱ **Histórico de cálculos** com os últimos 50 resultados
- 🎨 Interface escura moderna com efeito hover nos botões
- 🔢 Suporte a números decimais, porcentagem e inversão de sinal

---

## 🖥️ Pré-requisitos

- [JDK 8 ou superior](https://www.oracle.com/java/technologies/downloads/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (ou qualquer IDE Java)

---

## 🚀 Como executar

### Pelo IntelliJ IDEA
1. Clone o repositório
2. Abra o projeto no IntelliJ
3. Clique em ▶️ **Run** ou pressione `Shift + F10`

### Pelo Terminal
```bash
# Compilar
javac Calculadora.java

# Executar
java Calculadora
```

### Gerar o JAR executável
1. `File → Project Structure → Artifacts → + → JAR → From modules with dependencies`
2. Selecione `Calculadora` como Main Class → OK → Apply
3. `Build → Build Artifacts → Build`
4. O `.jar` estará em `out/artifacts/`

```bash
java -jar Calculadora.jar
```

---

## 📁 Estrutura do projeto

```
📦 calculadora-java
 ┗ 📜 Calculadora.java   # Código fonte completo
```

---

## 📸 Funcionalidades em destaque

| Modo Básico | Modo Científico | Histórico |
|---|---|---|
| 4 operações + % | 18 funções matemáticas | Últimos 50 cálculos |

---

## 🛠️ Tecnologias utilizadas

- **Java** — Linguagem principal
- **javax.swing** — Interface gráfica
- **java.awt** — Layout e eventos



---

> Desenvolvido com ☕ Java

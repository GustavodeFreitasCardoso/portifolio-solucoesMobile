import React, { useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { Provider as PaperProvider, Button, Text } from 'react-native-paper';

export default function App() {
  const [display, setDisplay] = useState('0');

  const adicionarValor = (valor) => {
    setDisplay((prev) => {
      if (prev === '0' || prev === 'Erro') return valor;
      return prev + valor;
    });
  };

  const limpar = () => {
    setDisplay('0');
  };

  const apagarUltimo = () => {
    setDisplay((prev) => {
      if (prev.length <= 1) return '0';
      return prev.slice(0, -1);
    });
  };

  const calcular = () => {
    try {
      const expressao = display.replace(/×/g, '*').replace(/÷/g, '/');

      const resultado = Function(`"use strict"; return (${expressao})`)();

      if (!isFinite(resultado)) {
        setDisplay('Erro');
        return;
      }

      setDisplay(String(resultado));
    } catch {
      setDisplay('Erro');
    }
  };

  return (
    <PaperProvider>
      <View style={styles.container}>

        <View style={styles.displayContainer}>
          <Text style={styles.displayText}>
            {display}
          </Text>
        </View>

        <View style={styles.buttons}>

          {/* Linha 1 */}
          <View style={styles.row}>
            <Button mode="contained" style={styles.grayButton} onPress={limpar}>C</Button>
            <Button mode="contained" style={styles.grayButton} onPress={apagarUltimo}>⌫</Button>
            <Button mode="contained" style={styles.orangeButton} onPress={() => adicionarValor('÷')}>÷</Button>
          </View>

          {/* Linha 2 */}
          <View style={styles.row}>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('7')}>7</Button>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('8')}>8</Button>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('9')}>9</Button>
            <Button mode="contained" style={styles.orangeButton} onPress={() => adicionarValor('×')}>×</Button>
          </View>

          {/* Linha 3 */}
          <View style={styles.row}>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('4')}>4</Button>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('5')}>5</Button>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('6')}>6</Button>
            <Button mode="contained" style={styles.orangeButton} onPress={() => adicionarValor('-')}>-</Button>
          </View>

          {/* Linha 4 */}
          <View style={styles.row}>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('1')}>1</Button>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('2')}>2</Button>
            <Button mode="contained" style={styles.numberButton} onPress={() => adicionarValor('3')}>3</Button>
            <Button mode="contained" style={styles.orangeButton} onPress={() => adicionarValor('+')}>+</Button>
          </View>

          {/* Linha 5 */}
          <View style={styles.row}>
            <Button
              mode="contained"
              style={styles.zeroButton}
              onPress={() => adicionarValor('0')}
            >
              0
            </Button>

            <Button
              mode="contained"
              style={styles.numberButton}
              onPress={() => adicionarValor('.')}
            >
              .
            </Button>

            <Button
              mode="contained"
              style={styles.orangeButton}
              onPress={calcular}
            >
              =
            </Button>
          </View>

        </View>
      </View>
    </PaperProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    justifyContent: 'flex-end',
    padding: 20,
  },

  displayContainer: {
    minHeight: 180,
    justifyContent: 'flex-end',
    alignItems: 'flex-end',
    marginBottom: 30,
    paddingHorizontal: 10,
  },

  displayText: {
    color: '#fff',
    fontSize: 56,
    fontWeight: '300',
  },

  buttons: {
    gap: 12,
  },

  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    gap: 12,
  },

  numberButton: {
    flex: 1,
    height: 75,
    justifyContent: 'center',
    backgroundColor: '#2b2b2b',
    borderRadius: 40,
  },

  grayButton: {
    flex: 1,
    height: 75,
    justifyContent: 'center',
    backgroundColor: '#a5a5a5',
    borderRadius: 40,
  },

  orangeButton: {
    flex: 1,
    height: 75,
    justifyContent: 'center',
    backgroundColor: '#ff9500',
    borderRadius: 40,
  },

  zeroButton: {
    flex: 2,
    height: 75,
    justifyContent: 'center',
    backgroundColor: '#2b2b2b',
    borderRadius: 40,
  },
});
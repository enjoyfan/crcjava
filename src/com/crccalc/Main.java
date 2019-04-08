package com.crccalc;

import static com.crccalc.Crc16.Crc16Arc;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        Check(Crc8.Params);
        Check(Crc16.Params);
        Check(Crc32.Params);
        Check(Crc64.Params);

        String hexString = "FF C1 00 63 01 83 00 00";
        byte[] bytes = HexUtils.hexStr2Bytes(hexString);

        AlgoParams[] algos = new AlgoParams[] {
                Crc16Arc,
        };
//        AlgoParams[] algos = Crc16.Params;

        for (int i = 0; i < algos.length; ++i) {
            CrcCalculator calc = new CrcCalculator(algos[i]);
            long result = calc.Calc(bytes, 0, bytes.length);
            System.out.printf("%s: 0x%04X\n", algos[i].Name, result);
        }
    }

    private static void Check(AlgoParams[] params) {
        for (int i = 0; i < params.length; i++) {
            CrcCalculator calculator = new CrcCalculator(params[i]);
            long result = calculator.Calc(CrcCalculator.TestBytes, 0, CrcCalculator.TestBytes.length);
            if (result != calculator.Parameters.Check)
                out.println(calculator.Parameters.Name + " - BAD ALGO!!! " + Long.toHexString(result).toUpperCase());
        }
    }
}
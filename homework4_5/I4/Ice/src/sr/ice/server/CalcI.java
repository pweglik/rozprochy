package sr.ice.server;

import Demo.Calc;
import com.zeroc.Ice.Current;
import java.lang.Math;

import java.util.List;

public class CalcI implements Calc {
	private static final long serialVersionUID = -2448962912780867770L;
	int precision = 8;

	@Override
	public float add(float a, float b, Current __current) {
		System.out.println("ADD");

		float result = a + b;

		double factor = Math.pow(10, this.precision);
		int temp = (int)(result * factor);
		result = (float)((float)(temp) / factor);

		return result;
	}

	@Override
	public float avg(float[] numbers, java.util.Optional<java.lang.Boolean> geometric, Current __current) {
		System.out.println("AVG");

		float avg;

		if (geometric.isPresent() && geometric.get() == true) {
			float sum = 1;
			for (float value : numbers) {
				sum *= value;
			}
			avg = (float)Math.sqrt((double)sum);
		}
		else {
			float sum = 0;
			for (float value : numbers) {
				sum += value;
			}
			avg = sum / numbers.length;
		}

		double factor = Math.pow(10, this.precision);
		int temp = (int)(avg * factor);
		avg = (float)((float)(temp) / factor);

		return avg;
	}

	@Override
	public void setPrecision(int precision, Current __current) {
		System.out.println("SET PRECISION");

		this.precision = precision;
	}
}
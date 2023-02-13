import kotlin.math.cos
import kotlin.math.sin

fun fft(signal: List<ComplexNumber>): List<ComplexNumber> {
    val n = signal.size
    if (n == 1) return signal

    val evenSignal = List(n / 2) { signal[2 * it] }
    val oddSignal = List(n / 2) { signal[2 * it + 1] }

    val evenFFT = fft(evenSignal)
    val oddFFT = fft(oddSignal)

    val result = List(n) { ComplexNumber(0.0, 0.0) }
    for (k in 0 until n / 2) {
        val t = oddFFT[k] * ComplexNumber(cos(2 * Math.PI * k / n), -sin(2 * Math.PI * k / n))
        result[k] = evenFFT[k] + t
        result[k + n / 2] = evenFFT[k] - t
    }

    return result
}

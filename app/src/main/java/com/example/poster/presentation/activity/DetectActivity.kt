package com.example.poster.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poster.R
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.poster.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder


class DetectActivity : AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var confidence: TextView
    private lateinit var imageView: ImageView
    private lateinit var picture: Button
    private val imageSize = 224

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detect)

        result = findViewById(R.id.result)
        confidence = findViewById(R.id.confidence)
        imageView = findViewById(R.id.imageView)
        picture = findViewById(R.id.button)


        // проверка разрешение камеры
        picture.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, 1)
            } else {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }

    private fun classifyImage(image: Bitmap) {
        // Создание новой модели
        val model = Model.newInstance(applicationContext)

        // Создание буфера для входных данных модели
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        // Создание буфера для конвертации пикселей изображения в числовые значения
        val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        // Получение цветовых значений пикселей изображения и конвертация их в числовые значения
        val intValues = IntArray(imageSize * imageSize)
        image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)
        var pixel = 0
        for (i in 0 until  imageSize){
            for (j in 0 until  imageSize){
                val value = intValues[pixel++] //RGB
                byteBuffer.putFloat(((value shr 16) and 0xFF) * (1f / 255f))
                byteBuffer.putFloat(((value shr 8) and 0xFF) * (1f / 255f))
                byteBuffer.putFloat((value and 0xFF) * (1f / 255f))
            }
        }
        // Загрузка данных в буфер
        inputFeature0.loadBuffer(byteBuffer)

        // Обработка данных моделью и получение результатов
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        // Определение класса с максимальной оценкой
        val confidences = outputFeature0.floatArray
        var maxPos = 0
        var maxConfidence = 0f
        for (i in confidences.indices){
            if(confidences[i] > maxConfidence){
                maxConfidence = confidences[i]
                maxPos = i
            }
        }
        // Задание результата
        val classes = arrayOf("Albert", "Phone")
        result.text = classes[maxPos]

        // Формирование строки с оценкой для каждого класса
        var s = ""
        for (i in classes.indices){
            s += String.format("%s: %1f%%\n", classes[i],confidences[i] * 100)
        }
        // отображение оценки
        confidence.text = s

        // Закрытие модели для освобождения ресурсов
        model.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Обработка результата изображения с камеры
            var image: Bitmap = data?.extras?.get("data") as Bitmap
            val dimension = Math.min(image.width,image.height)
            image = ThumbnailUtils.extractThumbnail(image,dimension,dimension)
            imageView.setImageBitmap(image)

            // Масштабирование изображения до размера, ожидаемого моделью
            image = Bitmap.createScaledBitmap(image,imageSize,imageSize,false)

            // Классификация изображения
            classifyImage(image)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
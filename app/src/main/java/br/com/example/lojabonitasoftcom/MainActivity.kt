package br.com.example.lojabonitasoftcom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.example.lojabonitasoftcom.lista_item.FoodItem
import br.com.example.lojabonitasoftcom.model.Food


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            foodList(
            )
        }
    }
}

            @Composable
            fun foodList() {
                val foodList: MutableList<Food> = mutableListOf(
                    Food(
                        imgFood = R.drawable.food1,
                        foodName = "Petiscos",
                        foodDescription = "Petiscos que servem bem como acompanhamento para bebidas, especialmente as fermentadas.",
                        price = "R$70,00"
                    ),
                    Food(
                        imgFood = R.drawable.food2,
                        foodName = "Barca",
                        foodDescription = "Barca contendo peças da famosa culinária oriental.",
                        price = "R$80,00"
                    ),
                    Food(
                        imgFood = R.drawable.food3,
                        foodName = "Filé Mignon",
                        foodDescription = "Prato simples e elegante.",
                        price = "R$50,00"
                    ),
                    Food(
                        imgFood = R.drawable.food4,
                        foodName = "Arroz com Fritas e Filé a Milanesa",
                        foodDescription = "Almoço comum, clássico brasileiro.",
                        price = "R$30,00"
                    ),
                    Food(
                        imgFood = R.drawable.food5,
                        foodName = "Spaguetti",
                        foodDescription = "Prato comum à culinária Italiana, famoso e saboroso.",
                        price = "R$40,00"
                    )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                ) {
                    LazyColumn {
                        itemsIndexed(foodList) { position, food ->
                            FoodItem(food)
                        }
                    }
                }
            }









package br.com.example.lojabonitasoftcom

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import br.com.example.lojabonitasoftcom.lista_item.FoodItem
import br.com.example.lojabonitasoftcom.model.Food
import br.com.example.lojabonitasoftcom.ui.theme.LojaBonitaSoftcomTheme

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LojaBonitaSoftcomTheme {
                val items = listOf(
                    BottomNavigationItem (
                        title = "Casa",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false,
                    ),
                    BottomNavigationItem (
                        title = "Carrinho",
                        selectedIcon = Icons.Filled.ShoppingCart,
                        unselectedIcon = Icons.Outlined.ShoppingCart,
                        hasNews = false,
                        badgeCount = 5
                    ),
                    BottomNavigationItem (
                        title = "Configurações",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                        hasNews = true,
                    ),
                )
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Surface {
                    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                    Scaffold (
                        containerColor = Color.DarkGray,
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                    Text(text = "Loja Bonita")

                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Yellow),
                                navigationIcon = {
                                    IconButton(onClick = {/*TODO*/}) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Menu Lateral"
                                        )
                                    }
                                },
                                scrollBehavior = scrollBehavior
                            )
                        },
                        bottomBar = {
                            NavigationBar (
                                containerColor = Color.Yellow
                            ){

                                    items.forEachIndexed { index, item ->
                                        NavigationBarItem(
                                            selected = selectedItemIndex == index,
                                            onClick = {
                                                selectedItemIndex = index
                                                //navController.navigate(item.title)
                                                      },
                                            label = {
                                                Text(text = item.title)
                                            },
                                            icon = {
                                                BadgedBox (
                                                    badge = {
                                                        if(item.badgeCount != null) {
                                                            Badge {
                                                                Text( text = item.badgeCount.toString())
                                                            }
                                                        } else if (item.hasNews) {
                                                            Badge()
                                                        }
                                                    }
                                                )
                                                {
                                                    Icon(
                                                        imageVector = if (index == selectedItemIndex) {
                                                            item.selectedIcon
                                                        } else item.unselectedIcon,
                                                        contentDescription = item.title

                                                    )
                                                }
                                            })
                                    }
                            }
                        }
                    )
                    { paddingvalues ->
                        @Composable
                        fun foodList(){
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
                            Column (
                                modifier = Modifier
                                    .padding(paddingvalues)
                                    .fillMaxSize()
                                    .background(color = Color.Black)
                            ) {
                                LazyColumn  {
                                    itemsIndexed(foodList){ position, food ->
                                        FoodItem(food)
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}






package com.zanahtech.basiclayout

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zanahtech.basiclayout.ui.theme.BasicLayoutTheme
import com.zanahtech.basiclayout.ui.theme.BlueBL
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavBotSheet()

                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBotSheet(){
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val context = LocalContext.current.applicationContext
    
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = { 
            ModalDrawerSheet {
                Box (modifier = Modifier
                    .background(BlueBL)
                    .fillMaxWidth()
                    .height(150.dp))
                {
                    Text(text = "Zanah APP" )
                }
                Divider()
                NavigationDrawerItem(label = { Text(text = "Home", color = BlueBL) },
                    selected = false,
                    icon = { Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = BlueBL
                    ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Home.screen){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Profile", color = BlueBL) },
                    selected = false,
                    icon = { Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = BlueBL
                    ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Screens.Profile.screen){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Logout", color = BlueBL) },
                    selected = false,
                    icon = { Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout",
                        tint = BlueBL
                    ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT)
//                        navigationController.navigate(Screens.Home.screen){
//                            popUpTo(0)
//                        }
                    })
                
            }
        },

        ) {
        Scaffold (
            topBar = {
                val coroutineScope = rememberCoroutineScope()
                TopAppBar(title = { Text(text = "WhatsApp")},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BlueBL,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Rounded.Menu, contentDescription = "MenuButton"
                            )
                        }
                    },
                    )

            },
            bottomBar = {
                BottomAppBar (containerColor = BlueBL){
                    IconButton(onClick = {
                        selected.value = Icons.Default.Home
                        navigationController.navigate(Screens.Home.screen){
                            popUpTo(0)

                    }
                    },
                        modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Home) Color.White else Color.Cyan
                        )

                    }


                    IconButton(onClick = {
                        selected.value = Icons.Default.Search
                        navigationController.navigate(Screens.Search.screen){
                            popUpTo(0)

                        }
                    },
                        modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Search) Color.White else Color.Cyan
                        )

                    }

                    Box  (modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                        contentAlignment = Alignment.Center
                    )  {
                        FloatingActionButton(onClick = { showBottomSheet = true}) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = BlueBL)
                        }




                    }
                    IconButton(onClick = {
                        selected.value = Icons.Default.Notifications
                        navigationController.navigate(Screens.Notification.screen){
                            popUpTo(0)

                        }
                    },
                        modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Notifications, contentDescription = null, modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Notifications) Color.White else Color.Cyan
                        )

                    }
                    IconButton(onClick = {
                        selected.value = Icons.Default.Person
                        navigationController.navigate(Screens.Profile.screen){
                            popUpTo(0)

                        }
                    },
                        modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(26.dp),
                            tint = if (selected.value == Icons.Default.Person) Color.White else Color.Cyan
                        )

                    }







                }
            }


        ){

            NavHost(navController = navigationController,
                startDestination = Screens.Home.screen  ){
                composable(Screens.Home.screen){ Home()}
                composable(Screens.Profile.screen){ Profile() }
                composable(Screens.Settings.screen){ Settings() }
                composable(Screens.Search.screen){ Search() }
                composable(Screens.Notification.screen){ Notification() }
                composable(Screens.Post.screen){ Post() }



            }

        }

        if (showBottomSheet){
            ModalBottomSheet(onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
                ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    BottomSheetItem(icon = Icons.Default.AddCircle, title ="Create a post" ) {
                        showBottomSheet = false
                        navigationController.navigate(Screens.Post.screen){
                            popUpTo(0)
                        }
                    }

                    BottomSheetItem(icon = Icons.Default.Star, title ="Add a Story" ) {
                        Toast.makeText(context, "Story", Toast.LENGTH_SHORT).show()
                    }

                    BottomSheetItem(icon = Icons.Default.PlayArrow, title ="Create a Reel" ) {
                        Toast.makeText(context, "Reels", Toast.LENGTH_SHORT).show()
                    }

                    BottomSheetItem(icon = Icons.Default.Favorite, title ="Go Live" ) {
                        Toast.makeText(context, "Live", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }








        
    }

}


@Composable
fun BottomSheetItem (icon: ImageVector, title: String, onClick: () -> Unit){
    Row (verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.clickable { onClick }
    )
    {
        Icon(icon, contentDescription = null, tint = BlueBL)
        Text(text = title, color = BlueBL, fontSize = 22.sp)

    }
}
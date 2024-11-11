package com.example.menutypesapp

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnPopupMenu: Button
    private lateinit var btnContextMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los botones
        btnPopupMenu = findViewById(R.id.btnPopupMenu)
        btnContextMenu = findViewById(R.id.btnContextMenu)

        // Configurar Menú Popup
        btnPopupMenu.setOnClickListener {
            val popupMenu = androidx.appcompat.widget.PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.popup_share -> showToast("Share selected")
                    R.id.popup_save -> showToast("Save selected")
                }
                true
            }
            popupMenu.show()
        }

        // Registrar Menú Contextual
        registerForContextMenu(btnContextMenu)
    }

    // Crear Menú de Opciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                showToast("Settings selected")
                true
            }
            R.id.action_about -> {
                showToast("About selected")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Crear Menú Contextual
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.context_edit -> {
                showToast("Edit selected")
                true
            }
            R.id.context_delete -> {
                showToast("Delete selected")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Método para mostrar mensajes Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

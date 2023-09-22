package me.bzvol.hadoopplugin

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.util.PlatformIcons.CLASS_ICON

class CreateKotlinMapReduceFileAction :
    CreateFileFromTemplateAction("MapReduce File (Kotlin)", "Create a new MapReduce file with Kotlin", CLASS_ICON) {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("New MapReduce File (Kotlin)")
            .addKind("Mapper", CLASS_ICON, "MapReduce Mapper Kt")
            .addKind("Reducer", CLASS_ICON, "MapReduce Reducer Kt")
            .addKind("Driver", CLASS_ICON, "MapReduce Driver Kt")
    }

    override fun getActionName(directory: PsiDirectory, newName: String, templateName: String): String {
        return "Create Kotlin MapReduce File: $newName"
    }
}
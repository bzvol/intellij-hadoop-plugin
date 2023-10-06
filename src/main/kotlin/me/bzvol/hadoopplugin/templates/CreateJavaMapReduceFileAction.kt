package me.bzvol.hadoopplugin.templates

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.util.PlatformIcons.CLASS_ICON

class CreateJavaMapReduceFileAction :
    CreateFileFromTemplateAction("MapReduce File (Java)", "Create a new MapReduce file with Java", CLASS_ICON) {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("New MapReduce File (Java)")
            .addKind("Mapper", CLASS_ICON, "MapReduce Mapper")
            .addKind("Reducer", CLASS_ICON, "MapReduce Reducer")
            .addKind("Driver", CLASS_ICON, "MapReduce Driver")
    }

    override fun getActionName(directory: PsiDirectory, newName: String, templateName: String): String {
        return "Create Java MapReduce File: $newName"
    }
}
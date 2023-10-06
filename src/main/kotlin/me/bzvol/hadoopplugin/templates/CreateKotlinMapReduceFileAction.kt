package me.bzvol.hadoopplugin.templates

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.util.PlatformIcons.CLASS_ICON
import com.intellij.util.PlatformIcons.INTERFACE_ICON

class CreateKotlinMapReduceFileAction :
    CreateFileFromTemplateAction("MapReduce File (Kotlin)", "Create a new MapReduce file with Kotlin", CLASS_ICON) {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("New MapReduce File (Kotlin)")
            .addKind("Mapper", CLASS_ICON, "MapReduce Mapper Kt")
            .addKind("Reducer", CLASS_ICON, "MapReduce Reducer Kt")
            .addKind("Driver", INTERFACE_ICON, "MapReduce Driver Kt")
    }

    override fun getActionName(directory: PsiDirectory, newName: String, templateName: String): String {
        return "Create Kotlin MapReduce File: $newName"
    }

    override fun createFileFromTemplate(name: String, template: FileTemplate, dir: PsiDirectory): PsiFile {
        val fileName = when (template.name) {
            "MapReduce Mapper Kt" -> name + "Mapper"
            "MapReduce Reducer Kt" -> name + "Reducer"
            "MapReduce Driver Kt" -> name + "Driver"
            else -> name
        }

        val packageName = dir.packageName
        if (packageName.isNotBlank())
            template.text = template.text.replace("\${PACKAGE_NAME}", packageName)
        else
            template.text = template.text.replace("package \${PACKAGE_NAME}\n\n", "")

        return super.createFileFromTemplate(fileName, template, dir)
    }

    companion object {
        @Suppress("RecursivePropertyAccessor")
        val PsiDirectory.packageName: String
            get() {
                val parent = this.parentDirectory
                val fullName = if (parent == null || parent.packageName.isEmpty()) this.name
                else "${parent.packageName}.${this.name}"
                return fullName.split(Regex("src\\.(main|test)\\.(kotlin|java)")).last()
            }
    }
}
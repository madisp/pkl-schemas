plugins {
  alias(libs.plugins.pkl)
}

val tag = providers.environmentVariable("GITHUB_TAG")

pkl {
  evaluators {
    val examples = listOf("apiWithExamples", "callbackExample", "linkExample", "petstore", "petstoreExpanded", "uspto")
    examples.forEach { exampleName ->
      register("example_${exampleName}") {
        sourceModules.add(file("examples/${exampleName}.pkl"))
        outputFile.set(layout.buildDirectory.file("generated/${exampleName}.yaml"))
        outputFormat.set("yaml")
      }
    }
  }

  project {
    packagers {
      register("makePackages") {
        projectDirectories.from(file("."))
        environmentVariables = tag.map { mapOf("PKL_PUBLISH_VERSION" to it.removePrefix("v")) }.orElse(emptyMap())
      }
    }
  }
}

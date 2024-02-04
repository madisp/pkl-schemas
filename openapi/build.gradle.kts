plugins {
  alias(libs.plugins.pkl)
}

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
}

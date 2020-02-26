(ns clj.new.lib-template
  (:require [clj.new.templates :refer [renderer project-data ->files]]))

(defn lib-template
  "entry point to run template."
  [name]
  (let [render (renderer "lib-template")
        data   (project-data name)]
    (println "Generating project from library template https://github.com/redstarssystems/lib-template.git")
    (->files data
      [".clj-kondo/config.edn" (render ".clj-kondo/config.edn" data)]
      ["dev/src/user.clj" (render "dev/src/user.clj" data)]
      ["resources/readme.txt" (render "resources/readme.txt" data)]
      ["src/{{nested-dirs}}/core.clj" (render "src/core.clj" data)]
      ["test/{{nested-dirs}}/core_test.clj" (render "test/core_test.clj" data)]
      [".editorconfig" (render ".editorconfig" data)]
      [".env" (render ".env" data)]
      [".envrc" (render ".envrc" data)]
      [".gitignore" (render ".gitignore" data)]
      ["CHANGELOG.adoc" (render "CHANGELOG.adoc" data)]
      ["deps.edn" (render "deps.edn" data)]
      ["LICENSE" (render "LICENSE" data)]
      ["Makefile" (render "Makefile" data)]
      ["pbuild.edn" (render "pbuild.edn" data)]
      ["README.adoc" (render "README.adoc" data)]
      ["tests.edn" (render "tests.edn" data)]
      )))


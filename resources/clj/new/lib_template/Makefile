SHELL=/bin/bash

CURRENT_TIME = $(shell date)

.EXPORT_ALL_VARIABLES:

.PHONY:	clean repl javac build install deploy conflicts release bump test help

YELLOW_PRINT = \033[0;33m
GREEN_PRINT = \033[0;92m
NORMAL_TEXT = \033[0m

define cecho
	@echo '-----------------------------------------------'
	@echo -e "$(GREEN_PRINT)$(CURRENT_TIME)"
	@echo -e "$(YELLOW_PRINT)"
	@echo -e $(1)
	@echo -e "$(NORMAL_TEXT)"
endef

.DEFAULT_GOAL := help


####
## Project variables
####

export OS = $(shell uname -a)
export OS_NAME = $(shell uname -s | tr A-Z a-z)
ifeq ($(OS_NAME), darwin)
	TIMESTAMP = $(shell gdate --utc +%FT%T.%3NZ)
endif
ifeq ($(OS_NAME), linux)
	TIMESTAMP = $(shell date --utc +%FT%T.%3NZ)
endif

export BUILD_TIMESTAMP=${TIMESTAMP}
export PROJECT_ROOT_DIR=$(shell pwd)

export LICENSE=$(shell cat LICENSE)

requirements: ## Install requirement tools. MacOS only!
	$(call cecho, "Install requirement tools")
	@if [ $$OS_NAME = "darwin" ]; then \
		brew install coreutils direnv jenv; \
		brew cask install cljstyle; \
		xattr -r -d com.apple.quarantine /usr/local/bin/cljstyle; \
		echo "Don't forget about \`direnv\` hooks."; \
	fi


clean: ## Clean project
	$(call cecho,"Clean project")
	@clojure -M:pbuilder clean

repl: ## Run Clojure REPL
	$(call cecho,"Run Clojure REPL")
	@clojure -M:repl

javac: ## Compile java classes
	$(call cecho,"Compile java classes")
	@clojure -M:pbuilder javac

build: ## Build jar file (library)
	$(call cecho,"Build jar file (library)")
	@clojure -M:pbuilder jar

install: ## Install jar file to local .m2
	$(call cecho,"Install jar file to local .m2")
	@clojure -M:pbuilder install

conflicts: ## Show class conflicts (if any)
	$(call cecho,"Show class conflicts (if any)")
	@clojure -M:pbuilder conflicts

outdated: ## Check outdated artifacts
	$(call cecho,"Check outdated artifacts")
	@clojure -M:outdated

deploy: ## Deploy jar file to  remote repository
	$(call cecho,"Deploy jar file to remote repository")
	$(call cecho,"Not implemented yet")
#	@clojure -M:pbuilder deploy

release: ## Release artifact
	$(call cecho,"Release artifact")
	$(call cecho,"Not implemented yet")
#	@clojure -M:pbuilder release

test: ## Run tests
	$(call cecho,"Run tests")
	@clojure -M:test $(filter-out $@,$(MAKECMDGOALS)) # additional optional parameter may be :unit or :integration

bump: ## Bump version artifact in build file. Parameter should be one of: major, minor, patch, alpha, beta, rc
	$(call cecho,"Bump version artifact in build file.")
	@clojure -M:pbuilder bump $(filter-out $@,$(MAKECMDGOALS)) # parameter should be one of: major minor patch alpha beta rc qualifier

format: ## Format source code
	$(call cecho, "Format source code")
	cljstyle fix

lint: ## Lint source code
	$(call cecho, "Lint source code")
	clj-kondo --parallel --lint src:test/src:dev/src
	cljstyle check

help: ## Show help
	 @grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'


# these lines need to work with command line params.
%:
	@:

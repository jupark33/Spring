로컬 브랜치 중 이름이 'tags'로 시작하는 브랜치만 echo
$ for atag in $(git branch -a | grep -v HEAD | grep -v remotes | grep tag); do echo $atag; done

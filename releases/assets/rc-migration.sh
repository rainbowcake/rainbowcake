### Argument check ###

if [[ $# -eq 0 ]] ; then
    echo '✗ Please supply a Git repository path as an argument!'
    echo 'Example:'
    echo './rc-migration.sh ~/projects/MyProject'
    echo 'Example for Windows, in a Git bash:'
    echo './rc-migration.sh /c/projects/MyProject'
    exit 1
fi

repo=$1
echo "✓ Migrating repository at path $repo to RainbowCake 0.2.0"


### Change directory ###

cd "$repo"

if [[ $? -eq 0 ]]; then
    echo '✓ Found directory at the given path'
else
    echo "✗ Can't find the specified directory."
    exit 1
fi

### Git repository checks ###

if [[ -d .git ]]; then
  echo '✓ Found .git folder'
else
  echo "✗ Can't find Git repository!"
  exit 1
fi;

if ! git ls-files >& /dev/null; then
  echo '✗ Supplied path is not a Git repository'
  exit 1
else
  echo '✓ Git repository looks OK'
fi


### Git status check ###

test -z "$(git status --porcelain)"
if [[ $? -eq 0 ]]; then
  echo "✓ No untracked files in repository directory"
else
  echo '✗ You have untracked or uncommitted files in your repository directory!'
  echo '✗ Here they are:'
  git status --porcelain
  exit 1
fi


### Migration ###

echo '✓ Performing migration, just a moment...'

git ls-files *.kt | xargs sed -b -i \
    -e 's/hu\.autsoft\.rainbowcake/co\.zsmb\.rainbowcake/g' \
    -e 's/BaseViewModel/RainbowCakeViewModel/g' \
    -e 's/BaseFragment/RainbowCakeFragment/g' \
    -e 's/BaseActivity/RainbowCakeActivity/g' \
    -e 's/BaseApplication/RainbowCakeApplication/g' \
    -e 's/BaseModule/RainbowCakeModule/g' \
    -e 's/BaseComponent/RainbowCakeComponent/g'


### Epilogue ###

echo '✓ Migration done! Here are the files changed:'
git ls-files -m

echo '✓ Done.'

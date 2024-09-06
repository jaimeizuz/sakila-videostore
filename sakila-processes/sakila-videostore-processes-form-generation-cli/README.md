@See https://dev.to/chad_r_stewart/compile-a-single-executable-from-your-node-app-with-nodejs-20-and-esbuild-210j
@See https://nodejs.org/docs/latest-v20.x/api/single-executable-applications.html#single-executable-applications
@See https://esbuild.github.io/
@See https://github.com/evanw/esbuild/tree/main

Clone kie-tools repo
Navigate to packages/form-generation-tool

Exec:
    pnpm esbuild src/index.ts --bundle --platform=node --outfile=form-generation-cli-windows.js
    node --experimental-sea-config sea-config.json
    node -e "require('fs').copyFileSync(process.execPath, 'form-generation-cli-windows.exe')"
    npx postject form-generation-cli-windows.exe NODE_SEA_BLOB form-generation-cli-windows.blob --sentinel-fuse NODE_SEA_FUSE_fce680ab2cc467b6e072b8b5df1996b2
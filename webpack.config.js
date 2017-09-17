const path = require('path')
const webapp = 'src/main/webapp'
const jsPath = path.resolve(__dirname, webapp, 'scripts', 'index.js')
const scssPath = path.resolve(__dirname, webapp, 'styles', 'papes.scss')
const buildPath = path.resolve(__dirname, webapp)
const ExtractTextPlugin = require('extract-text-webpack-plugin')

module.exports = {
	entry: [jsPath, scssPath],
	output: {
		path: buildPath,
		filename: 'papes.js',
		publicPath: webapp
	},
	module: {
        loaders: [
            {
                test: /.*\.js$/,
                loader: 'babel-loader',
                include: path.join(__dirname, webapp, 'scripts'),
                exclude: /node_modules/,
                options: {
                    presets: ['es2015'],
                }
            },
            {
                test: /.*\.scss$/,
                loader: ExtractTextPlugin.extract([
                	'css-loader',
					'sass-loader',
				])
            }
		]
	},
	devServer: {
		port: 3030
	},
    plugins: [
		new ExtractTextPlugin({
            filename: 'papes.css',
			allChunks: true,
		}),
	],
}

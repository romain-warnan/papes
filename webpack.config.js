const path = require('path')
const webapp = 'src/main/webapp'
const indexPath = path.resolve(__dirname, webapp, 'scripts', 'index.js')
const buildPath = path.resolve(__dirname, webapp)

module.exports = {
	entry: [indexPath],
	output: {
		path: buildPath,
		filename: 'papes.js',
		publicPath: webapp
	},
	module: {
		rules: [
			{
				test: /.*\.js$/,
				include: path.join(__dirname, webapp, 'scripts'),
				loader: 'babel-loader',
				exclude: /node_modules/,
				options: {
					presets: ['es2015']
				}
			}
		]
	},
	devServer: {
		port: 3031
	}
}

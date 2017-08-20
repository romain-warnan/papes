module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        watch: {
            scripts: {
                files: ['src/main/webapp/scripts/*.js'],
                tasks: ['concat:dist', 'browserify:dev'],
                options: {
                    spawn: true,
                },
            },
        },
        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: ['src/main/webapp/scripts/*.js'],
                dest: 'src/main/webapp/index.js'
            }
        },
        browserify: {
            dist: {
                files: {
                    'src/main/webapp/index.js': ['src/main/webapp/index.js']
                }
            },
            dev: {
                files: {
                    'src/main/webapp/index.min.js': ['src/main/webapp/index.js']
                }
            }
        },
        babel: {
            options: {
                sourceMap: true,
                presets: ['env']
            },
            dist: {
                files: {
                    'src/main/webapp/index.js': 'src/main/webapp/index.js'
                }
            }
        },
        uglify: {
            dist: {
                files: {
                    'src/main/webapp/index.min.js': ['src/main/webapp/index.js']
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-browserify');
    grunt.loadNpmTasks('grunt-babel');
    grunt.loadNpmTasks('grunt-contrib-uglify');

    grunt.registerTask('default', ['concat:dist', 'browserify:dist', 'babel:dist', 'uglify:dist']);
    grunt.registerTask('dev', ['concat:dist', 'browserify:dev']);
};
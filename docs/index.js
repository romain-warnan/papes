const Chart = require('chart.js')
const c3 = require('c3')
const d3 = require('d3')
const _ = require('lodash');(function () {
    const context = document.getElementById('papes-par-saintete').getContext('2d')

    Chart.defaults.global.legend.position = 'bottom'

    fetch('data/papes-par-saintete.json')
        .then(response => response.json())
        .then(json => draw(json))

    const draw = (json) => {
        const labels = _.reverse(json.map(item => item.label))
        const values = _.reverse(json.map(item => item.value))

        const chart = new Chart(context, {
            type: 'doughnut',
            data: {
                datasets: [{
                    data: values,
                    borderColor: [
                        'rgba(251,147,20, 1)',
                        'rgba(65, 105, 225, 1)'
                    ],
                    backgroundColor: [
                        'rgba(251,147,20, 0.5)',
                        'rgba(65, 105, 225, 0.5)'
                    ],
                    borderWidth: 2
                }],
                labels: labels
            },
            options: {
                rotation: Math.PI,
                tooltips: {
                    callbacks: {
                        label: tooltipLabel
                    }
                }
            }
        })
    }

    const tooltipLabel = (tooltipItem, data) => {
        const index = tooltipItem.index
        const datasetIndex = tooltipItem.datasetIndex
        const values = data.datasets[datasetIndex].data
        const total = _.sum(values);
        const value = values[index]
        const percent = _.round(value * 100 / total)

        return `${percent} % (${value})`
    }
})();(function () {
    fetch('data/plus-courts-regnes.json')
        .then(response => response.json())
        .then(json => draw(json))

    const draw = (json) => {
        const labels = json
            .map(item => item.label)
            .map(label => label.substring(0, label.indexOf(' : ')))

        labels.unshift('labels')

        const values = json.map(item => item.value)
        values.unshift('values')

        const tooltipValues = json
            .map(item => item.label)
            .map(label => label.substring(label.indexOf(' : ') + 3, label.length))

        c3.generate({
            bindto: '#plus-courts-regnes',
            data: {
                type: 'bar',
                x: 'labels',
                columns: [labels, values],
                colors: {
                    values: '#a90f27'
                },
                color: function (color, d) {
                    return d.id && d.id === 'values' ? d3.rgb(color).brighter(0.13 * (9 - d.index)) : color
                }
            },
            axis: {
                rotated: true,
                x: {
                    type: 'category'
                }
            },
            tooltip: {
                format: {
                    name: (name, ratio, id, index) => 'Règne',
                    value: (value, ratio, id, index) => tooltipValues[index]
                }
            },
            legend: {
                hide: 'values'
            }
        });
    }
})();(function () {
    fetch('data/plus-longs-regnes.json')
        .then(response => response.json())
        .then(json => draw(json))

    const draw = (json) => {
        const labels = json
            .map(item => item.label)
            .map(label => label.substring(0, label.indexOf(' : ')))

        labels.unshift('labels')

        const values = json.map(item => item.value)
        values.unshift('values')

        const tooltipValues = json
            .map(item => item.label)
            .map(label => label.substring(label.indexOf(' : ') + 3, label.length))

        c3.generate({
            bindto: '#plus-longs-regnes',
            data: {
                type: 'bar',
                x: 'labels',
                columns: [labels, values],
                colors: {
                    values: '#009688'
                },
                color: function (color, d) {
                    return d.id && d.id === 'values' ? d3.rgb(color).brighter(0.13 * d.index) : color
                }
            },
            axis: {
                rotated: true,
                x: {
                    type: 'category'
                }
            },
            tooltip: {
                format: {
                    name: (name, ratio, id, index) => 'Règne',
                    value: (value, ratio, id, index) => tooltipValues[index]
                }
            },
            legend: {
                hide: 'values'
            }
        });
    }
})()
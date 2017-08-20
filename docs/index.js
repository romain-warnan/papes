const Chart = require('chart.js')
const c3 = require('c3')
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
    const context = document.getElementById('plus-courts-regnes').getContext('2d')

    Chart.defaults.global.defaultColor = 'rgba(251,147,20,0.5)'

    fetch('data/plus-courts-regnes.json')
        .then(response => response.json())
        .then(json => draw(json))

    const draw = (json) => {
        const labels = json.map(item => item.label)
        const values = json.map(item => item.value)

        const chart = new Chart(context, {
            type: 'horizontalBar',
            data: {
                datasets: [{
                    data: values,
                    backgroundColor: [
                        '#a90f27',
                        '#b1263c',
                        '#ba3e52',
                        '#c25767',
                        '#cb6f7d',
                        '#d48793',
                        '#dc9fa8',
                        '#e5b7be',
                        '#edcfd3',
                        '#f6e7e9'
                    ]
                }],
                labels: labels
            },
            options: {
                legend: {
                    display: false
                },
                scales: {
                    xAxes: [{
                        ticks: {
                            min: 0
                        },
                        scaleLabel: {
                            display: true,
                            labelString: 'jours'
                        }
                    }]
                }
            }
        })
    }
})();(function () {
    fetch('data/plus-longs-regnes.json')
        .then(response => response.json())
        .then(json => draw(json))

    const draw = (json) => {
        const labels = json
            .map(item => item.label)
            .map(label => label.substring(0, label.indexOf(' : ')))

        labels.unshift('Papes')

        const values = json.map(item => item.value)
        values.unshift('Durée du pontificat en jours')

        const tooltipValues = json
            .map(item => item.label)
            .map(label => label.substring(label.indexOf(' : ') + 3, label.length))

        c3.generate({
            bindto: '#plus-longs-regnes',
            data: {
                type: 'bar',
                x: 'Papes',
                columns: [labels, values]
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
            }
        });
    }
})()
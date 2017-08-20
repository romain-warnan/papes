(function () {
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
                        }
                    }]
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
})()
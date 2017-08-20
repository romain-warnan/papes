(function () {

    const context = document.getElementById('plus-longs-regnes').getContext('2d')

    Chart.defaults.global.defaultColor = 'rgba(251,147,20,0.5)'

    fetch('data/plus-longs-regnes.json')
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
                        '#009688',
                        '#19a093',
                        '#32ab9f',
                        '#4cb5ab',
                        '#66c0b7',
                        '#7fcac3',
                        '#99d5cf',
                        '#b2dfdb',
                        '#cceae7',
                        '#e5f4f3'
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
})()
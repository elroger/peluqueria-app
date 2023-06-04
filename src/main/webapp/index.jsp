<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Peluqueria New York">
    <meta name="Roger y Sandra" content="">

    <title>Peluquería || New York</title>

    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">

    <!-- Elegant Font Icons CSS -->
    <link rel="stylesheet" href="css/elegant-font-icons.css">
    <!-- Elegant Line Icons CSS -->
    <link rel="stylesheet" href="css/elegant-line-icons.css">
    <!-- Themify Icon CSS -->
    <link rel="stylesheet" href="css/themify-icons.css">
    <!-- Barber Icons CSS -->
    <link rel="stylesheet" href="css/barber-icons.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- animate CSS -->
    <link rel="stylesheet" href="css/animate.min.css">
    <!-- Venobox CSS -->
    <link rel="stylesheet" href="css/venobox/venobox.css">
    <!-- Nice Select CSS -->
    <link rel="stylesheet" href="css/nice-select.css">
    <!-- OWL-Carousel CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <!-- Slick Nav CSS -->
    <link rel="stylesheet" href="css/slicknav.min.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="css/main.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">

    <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>

    <!-- Estilos para el video y desplegable formuralio, filter es la opacidad a oscuro-->
    <style>
        .video_bg {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
            filter: brightness(0.6);
        }

        select.form-control option {
            color: #000;
        }
    </style>

</head>

<body>

    <!-- Cargando inicial -->
    <div id="preloader">
        <div class="loader">
            <img src="img/loading.gif" width="80" alt="">
        </div>
    </div>

    <!-- Header -->
    <header id="header" class="header-section">
        <div class="container">
            <nav class="navbar">
                <a href="/peluqueria-app/" class="navbar-brand"><img src="img/logo.png" alt="Barbershop"></a>
                <div class="d-flex menu-wrap align-items-center">
                    <div id="mainmenu" class="mainmenu">
                        <ul class="nav">
                            <li>
                                <a href="#about">Info</a>
                            </li>
                            <li>
                                <a href="#service_section">Servicios</a>
                            </li>
                            <li>
                                <a href="#footer">Contacto</a>
                            </li>
                            <li>
                                <a href="admin.jsp" style="color: white; font-weight: bold;">ADMNISTRACIÓN</a>
                            </li>
                        </ul>
                    </div>
                    <div class="header-btn">
                        <a href="#appointment_form" class="menu-btn">Pedir una cita</a>
                    </div>
                    <div class="language-selector">
                        <ul class="nav">
                            <li class="language-option active">
                                <a href="/peluqueria-app/" data-lang="es">
                                    <img src="img/flag/1.png" alt="Español" width="20" height="20">
                                </a>
                            </li>
                            <li class="language-option">
                                <a href="indexEng.jsp" data-lang="en">
                                    <img src="img/flag/2.png" alt="English" width="20" height="20">
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </header>

    <!-- Video en bucle -->
    <section class="hero_section d-flex align-items-center">
        <video class="video_bg" autoplay muted loop>
            <source src="vid/promoPeluqueria.mp4" type="video/mp4">
        </video>
        <div class="container">
            <div class="hero_content align-center">
                <h3>No es solo un corte de pelo, es toda una experiencia.</h3>
                <h1>Ser peluquero <br>es cuidar de las personas.</h1>
                <p>Nuestra barbería es el lugar perfecto para hombres que valoran<br> la calidad, el tiempo y el estilo.</p>
                <a href="#appointment_form" class="default_btn">Haz tu cita</a>
            </div>
        </div>
    </section>

    <!-- Sobre Nosotros -->
    <section id="about" class="about_section bd-bottom padding">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="about_content align-center">
                        <h3 class="wow fadeInUp" data-wow-delay="100ms">Peluquería New York</h3>
                        <h2 class="wow fadeInUp" data-wow-delay="200ms">Peluqueros <br>desde el 2008</h2>
                        <img class="wow fadeInUp" data-wow-delay="500ms" src="img/logoBlack.png" alt="logo">
                        <p class="wow fadeInUp" data-wow-delay="600ms">Tu cabello merece lo mejor, y nosotros lo sabemos.<br>Nuestra peluquería no solo te brinda un corte de pelo, sino también una experiencia única que reflejará tu estilo y personalidad.</p>
                    </div>
                </div>
                <div class="col-md-6 d-none d-md-block">
                    <div class="about_img">
                        <img src="img/about-1.jpg" alt="idea-images" class="about_img_1 wow fadeInLeft" data-wow-delay="200ms">
                        <img src="img/about-2.jpg" alt="idea-images" class="about_img_2 wow fadeInRight" data-wow-delay="400ms">
                        <img src="img/about-3.jpg" alt="idea-images" class="about_img_3 wow fadeInLeft" data-wow-delay="600ms">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Estos son nuestros servicios -->
    <section id="service_section" class="service_section bg-grey bd-bottom padding">
        <div class="container">
            <div class="section_heading text-center mb-40 wow fadeInUp" data-wow-delay="300ms">
                <h3>Plan invita a un amigo: por cada 5 amigos que traigas recibirás un corte de pelo gratis.</h3>
                <h2>Estos son nuestros servicios</h2>
                <div class="heading-line"></div>
            </div>
            <div class="row">
                <div class="col-lg-3 col-md-6 sm-padding">
                    <div class="service_content align-center wow fadeInUp" data-wow-delay="200ms">
                        <img src="img/service-1.jpg" alt="Services">
                        <h3>Corte de pelo</h3>
                        <P>Servicio principal de la peluquería, corte para hombres según sus preferencias.</P>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 sm-padding wow fadeInUp" data-wow-delay="300ms">
                    <div class="service_content align-center">
                        <img src="img/service-2.jpg" alt="Services">
                        <h3>Afeitado</h3>
                        <P>Servicio de afeitado de barba y bigote con navaja, para una piel suave y libre de vellos no deseados.</P>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 sm-padding wow fadeInUp" data-wow-delay="400ms">
                    <div class="service_content align-center">
                        <img src="img/service-3.jpg" alt="Services">
                        <h3>Arreglo de barba:</h3>
                        <P>Dar forma y estilo a la barba del cliente, cortando, recortando y aplicando productos de cuidado.</P>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 sm-padding wow fadeInUp" data-wow-delay="500ms">
                    <div class="service_content align-center">
                        <img src="img/service-4.jpg" alt="Services">
                        <h3>Coloración de cabello:</h3>
                        <P>Servicio de cambio de color para cabello gris o para cambiar el estilo con tintes y decolorantes.</P>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Formulario de Citas -->
    <section id="appointment_form" class="book_section padding">
        <div class="book_bg"></div>
        <div class="map_pattern"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-6">
                    <div class="insert-button">
                        <button class="default_btn" onclick="insertarDatosPrueba()">Insertar datos de prueba</button>
                    </div>
                    <form action="BookingServlet" method="post" id="appointment_form" class="form-horizontal appointment_form">

                        <div class="form-group">
                            <input type="text" id="app_name" name="app_name" class="form-control" placeholder="Nombre" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <input type="email" id="app_email" name="app_email" class="form-control" placeholder="Email" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <input type="text" id="app_phone" name="app_phone" class="form-control" placeholder="Telefono" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <input type="date" id="app_date" name="app_date" class="form-control" placeholder="Fecha" required>
                        </div>
                        <div class="form-group">
                            <input type="time" id="app_time" name="app_time" class="form-control" placeholder="Hora" required>
                        </div>
                        <div class="form-group">
                            <select class="form-control black-select" id="app_service" name="app_service">
                                <option>Corte Normal</option>
                                <option>Afeitado</option>
                                <option>Arreglo de barba</option>
                                <option>Coloración de cabello</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <select class="form-control black-select" id="app_barber" name="app_barber">
                                <option>Luis Alberto</option>
                                <option>Juan Carlos</option>
                                <option>Ramón Antonio</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="default_btn">Confirmar Cita</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>


    <section class="cta_section padding">
        <div class="container">
            <div class="row">
                <div class="col-md-6 align-self-center">
                    <div class="cta_content">
                        <h2>Atendemos con calidad a todos nuestros clientes</h2>
                        <p>Tu satisfacción es nuestra prioridad.</p>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="cta_btn">
                        <a href="#appointment_form" class="default_btn">Reserva tu cita ahora</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer id="footer" class="footer_section">
        <div class="footer_bg"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="footer_widget">
                        <h3 class="footer_title">
                            Acerca de nosotros
                        </h3>
                        <p>Somos una peluquería especializada en ofrecer servicios de alta calidad para hombres. Nuestra misión es brindar a nuestros clientes un ambiente acogedor y servicios de peluquería profesionales.</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="footer_widget">
                        <h3 class="footer_title">
                            Nuestros Servicios
                        </h3>
                        <ul class="footer_nav">
                            <li><a href="#service_section">Corte de pelo</a></li>
                            <li><a href="#service_section">Afeitado</a></li>
                            <li><a href="#service_section">Arreglo de barba</a></li>
                            <li><a href="#service_section">Coloración de cabello</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="footer_widget">
                        <h3 class="footer_title">
                            Horario de Atención
                        </h3>
                        <p>Abierto de lunes a sábado ininterrumpido</p>
                        <p>9:00 am - 8:00 pm</p>
                        <p>Domingo cerrado</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="footer_widget">
                        <h3 class="footer_title">
                            Contacto
                        </h3>
                        <p>Calle Falsa #123</p>
                        <p>Madrid, España</p>
                        <p>contacto@peluquerianewyork.com</p>
                        <p>+34 123 456 789</p>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery JS -->
    <script>
        function insertarDatosPrueba() {
            // Rellenar datos de prueba
            document.getElementById('app_name').value = 'Albert Einstein';
            document.getElementById('app_email').value = 'albert.einstein@relatividad.com';
            document.getElementById('app_phone').value = '684233454';
            document.getElementById('app_date').value = '2024-01-01';
            document.getElementById('app_time').value = '09:00';
            document.getElementById('app_service').value = 'Corte Normal';
            document.getElementById('app_barber').value = 'Luis Alberto';
        }
    </script>

    <script src="js/vendor/jquery-1.12.4.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Wow JS -->
    <script src="js/wow.min.js"></script>
    <!-- Venobox JS -->
    <script src="js/venobox.min.js"></script>
    <!-- Nice Select JS -->
    <script src="js/nice-select.min.js"></script>
    <!-- OWL-Carousel JS -->
    <script src="js/owl.carousel.min.js"></script>
    <!-- Slick Nav JS -->
    <script src="js/slicknav.min.js"></script>
    <!-- Easing JS -->
    <script src="js/jquery.easing.min.js"></script>
    <!-- ScrollUp JS -->
    <script src="js/jquery.scrollUp.min.js"></script>
    <!-- Main JS -->
    <script src="js/main.js"></script>

</body>

</html>
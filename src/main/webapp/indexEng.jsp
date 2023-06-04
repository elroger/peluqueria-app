<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Barbershop New York">
    <meta name="Roger y Sandra" content="">

    <title>Barbershop || New York</title>

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

    <!-- Styles for video and dropdown form, filter is the dark overlay -->
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

    <!-- Initial Loading -->
    <div id="preloader">
        <div class="loader">
            <img src="img/loading.gif" width="80" alt="">
        </div>
    </div>

    <!-- Header -->
    <header id="header" class="header-section">
        <div class="container">
            <nav class="navbar">
                <a href="indexEng.jsp" class="navbar-brand"><img src="img/logo.png" alt="Barbershop"></a>
                <div class="d-flex menu-wrap align-items-center">
                    <div id="mainmenu" class="mainmenu">
                        <ul class="nav">
                            <li>
                                <a href="#about">About</a>
                            </li>
                            <li>
                                <a href="#service_section">Services</a>
                            </li>
                            <li>
                                <a href="#footer">Contact</a>
                            </li>
                            <li>
                                <a href="admin.jsp" style="color: white; font-weight: bold;">MANAGEMENT</a>
                            </li>
                        </ul>
                    </div>
                    <div class="header-btn">
                        <a href="#appointment_form" class="menu-btn">Book an Appointment</a>
                    </div>
                    <div class="language-selector">
                        <ul class="nav">
                            <li class="language-option active">
                                <a href="/peluqueria-app/" data-lang="es">
                                    <img src="img/flag/1.png" alt="Español" width="20" height="20">
                                </a>
                            </li>
                            <li class="language-option">
                                <a href="indexEng.jsp" data-lang="es">
                                    <img src="img/flag/2.png" alt="English" width="20" height="20">
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </header>

    <!-- Video Loop -->
    <section class="hero_section d-flex align-items-center">
        <video class="video_bg" autoplay muted loop>
            <source src="vid/promoPeluqueria.mp4" type="video/mp4">
        </video>
        <div class="container">
            <div class="hero_content align-center">
                <h3>It's not just a haircut, it's a whole experience.</h3>
                <h1>Being a barber <br>is taking care of people.</h1>
                <p>Our barbershop is the perfect place for men who value quality, time, and style.</p>
                <a href="#appointment_form" class="default_btn">Make an Appointment</a>
            </div>
        </div>
    </section>

    <!-- About Us -->
    <section id="about" class="about_section bd-bottom padding">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="about_content align-center">
                        <h3 class="wow fadeInUp" data-wow-delay="100ms">Barbershop New York</h3>
                        <h2 class="wow fadeInUp" data-wow-delay="200ms">Barbers <br>since 2008</h2>
                        <img class="wow fadeInUp" data-wow-delay="500ms" src="img/logoBlack.png" alt="logo">
                        <p class="wow fadeInUp" data-wow-delay="600ms">Your hair deserves the best, and we know it. Our barbershop not only provides you with a haircut but also a unique experience that reflects your style and personality.</p>
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

    <!-- Our Services -->
    <section id="service_section" class="service_section bg-grey bd-bottom padding">
        <div class="container">
            <div class="section_heading text-center mb-40 wow fadeInUp" data-wow-delay="300ms">
                <h3>Invite a friend plan: for every 5 friends you bring, you'll get a free haircut.</h3>
                <h2>These are our services</h2>
                <div class="heading-line"></div>
            </div>
            <div class="row">
                <div class="col-lg-3 col-md-6 sm-padding">
                    <div class="service_content align-center wow fadeInUp" data-wow-delay="200ms">
                        <img src="img/service-1.jpg" alt="Services">
                        <h3>Haircut</h3>
                        <p>The main service of the barbershop, haircut for men according to their preferences.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 sm-padding wow fadeInUp" data-wow-delay="300ms">
                    <div class="service_content align-center">
                        <img src="img/service-2.jpg" alt="Services">
                        <h3>Shaving</h3>
                        <p>Beard and mustache shaving service with a razor, for smooth and hair-free skin.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 sm-padding wow fadeInUp" data-wow-delay="400ms">
                    <div class="service_content align-center">
                        <img src="img/service-3.jpg" alt="Services">
                        <h3>Beard Styling</h3>
                        <p>Shaping and styling the client's beard by cutting, trimming, and applying grooming products.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 sm-padding wow fadeInUp" data-wow-delay="500ms">
                    <div class="service_content align-center">
                        <img src="img/service-4.jpg" alt="Services">
                        <h3>Hair Coloring</h3>
                        <p>Color change service for gray hair or to change style with dyes and bleaches.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Appointment Form -->
    <section id="appointment_form" class="book_section padding">
        <div class="book_bg"></div>
        <div class="map_pattern"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-6">
                    <div class="insert-button">
                        <button class="default_btn" onclick="insertSampleData()">Insert Sample Data</button>
                    </div>
                    <form action="BookingServlet" method="post" id="appointment_form" class="form-horizontal appointment_form">

                        <div class="form-group">
                            <input type="text" id="app_name" name="app_name" class="form-control" placeholder="Name" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <input type="email" id="app_email" name="app_email" class="form-control" placeholder="Email" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <input type="text" id="app_phone" name="app_phone" class="form-control" placeholder="Phone" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <input type="date" id="app_date" name="app_date" class="form-control" placeholder="Date" required>
                        </div>
                        <div class="form-group">
                            <input type="time" id="app_time" name="app_time" class="form-control" placeholder="Time" required>
                        </div>
                        <div class="form-group">
                            <select class="form-control black-select" id="app_service" name="app_service">
                                <option>Normal Haircut</option>
                                <option>Shaving</option>
                                <option>Beard Styling</option>
                                <option>Hair Coloring</option>
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
                            <button type="submit" class="default_btn">Confirm Appointment</button>
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
                        <h2>We provide quality service to all our clients</h2>
                        <p>Your satisfaction is our priority.</p>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="cta_btn">
                        <a href="#appointment_form" class="default_btn">Book Your Appointment Now</a>
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
                            About Us
                        </h3>
                        <p>We are a specialized barbershop offering high-quality services for men. Our mission is to provide our clients with a welcoming environment and professional hairdressing services.</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="footer_widget">
                        <h3 class="footer_title">
                            Our Services
                        </h3>
                        <ul class="footer_nav">
                            <li><a href="#service_section">Haircut</a></li>
                            <li><a href="#service_section">Shaving</a></li>
                            <li><a href="#service_section">Beard Styling</a></li>
                            <li><a href="#service_section">Hair Coloring</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="footer_widget">
                        <h3 class="footer_title">
                            Opening Hours
                        </h3>
                        <p>Open from Monday to Saturday</p>
                        <p>9:00 am - 8:00 pm</p>
                        <p>Closed on Sunday</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="footer_widget">
                        <h3 class="footer_title">
                            Contact
                        </h3>
                        <p>Fake Street #123</p>
                        <p>Madrid, Spain</p>
                        <p>contact@newyorkbarbershop.com</p>
                        <p>+34 123 456 789</p>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery JS -->
    <script>
        function insertSampleData() {
            // Fill in sample data
            document.getElementById('app_name').value = 'Albert Einstein';
            document.getElementById('app_email').value = 'albert.einstein@relativity.com';
            document.getElementById('app_phone').value = '684233454';
            document.getElementById('app_date').value = '2024-01-01';
            document.getElementById('app_time').value = '09:00';
            document.getElementById('app_service').value = 'Normal Haircut';
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
